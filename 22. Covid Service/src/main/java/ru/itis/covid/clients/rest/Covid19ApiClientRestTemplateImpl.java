//package ru.itis.covid.clients.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import ru.itis.covid.clients.CovidClient;
//import ru.itis.covid.entries.Covid19ApiRecord;
//import ru.itis.covid.entries.CovidStatistic;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Component
//public class Covid19ApiClientRestTemplateImpl implements CovidClient {
//
//    @Value("${covid19.api.url}")
//    private String url;
//
//    @Autowired
//    private RestTemplate client;
//
//    @Override
//    public List<CovidStatistic> getAll() {
//        Covid19ApiRecord[] response = client.getForEntity(url, Covid19ApiRecord[].class).getBody();
//        List<Covid19ApiRecord> records = Arrays.asList(Objects.requireNonNull(response));
//        return records.parallelStream()
//                .map(record -> CovidStatistic.builder()
//                        .country(record.getCountryCode())
//                        .dateTime(record.getDate())
//                        .from("Covid19Api")
//                        .recovered(record.getRecovered())
//                        .build())
//                .collect(Collectors.toList());
//    }
//}
