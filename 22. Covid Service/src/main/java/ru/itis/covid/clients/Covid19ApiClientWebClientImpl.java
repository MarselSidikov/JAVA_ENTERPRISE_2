package ru.itis.covid.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.covid.entries.Covid19ApiRecord;
import ru.itis.covid.entries.CovidStatistic;
import java.util.Arrays;

@Component
public class Covid19ApiClientWebClientImpl implements CovidClient {

    // реактивная штука для отправки запросов на другие сервисы
    private WebClient client;

    public Covid19ApiClientWebClientImpl(@Value("${covid19.api.url}") String url) {
        client = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                                .build())
                .baseUrl(url)
                .build();
    }

    @Override
    public Flux<CovidStatistic> getAll() {
        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(Covid19ApiRecord[].class)) // преобразуем данные со стороннего сервреа в Publisher
                .flatMapIterable(Arrays::asList) // выполняем конвертацию данных с другого сервера в наши, возвращаем мы набор Publisher-ов(?) каждый из которых возвращает объект CovidStatistic
                .map(record ->
                        CovidStatistic.builder()
                                    .country(record.getCountryCode())
                                    .dateTime(record.getDate())
                                    .from("Covid19Api")
                                    .recovered(record.getRecovered())
                                    .build());
    }
}
