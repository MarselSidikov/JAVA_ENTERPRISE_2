package ru.itis.covid.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.covid.entries.Covid19ApiRecord;
import ru.itis.covid.entries.CovidStatistic;
import ru.itis.covid.entries.TheVirusTrackerResponse;

import java.util.Arrays;

@Component
public class TheVirusTrackerClientWebClientImpl implements CovidClient {

    // реактивная штука для отправки запросов на другие сервисы
    private WebClient client;

    public TheVirusTrackerClientWebClientImpl(@Value("${thevirustracker.url}") String url) {
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
                .flatMap(clientResponse -> clientResponse.bodyToMono(TheVirusTrackerResponse.class)) // преобразуем данные со стороннего сервреа в Publisher
                .flatMapIterable(TheVirusTrackerResponse::getData) // выполняем конвертацию данных с другого сервера в наши, возвращаем мы набор Publisher-ов(?) каждый из которых возвращает объект CovidStatistic
                .map(record ->
                        CovidStatistic.builder()
                                    .country(record.getCountryCode())
                                    .dateTime(record.getDate())
                                    .from("TheVirusTracker")
                                    .recovered(Integer.parseInt(record.getRecovered()))
                                    .build());
    }
}
