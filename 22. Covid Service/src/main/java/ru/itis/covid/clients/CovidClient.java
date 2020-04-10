package ru.itis.covid.clients;

import reactor.core.publisher.Flux;
import ru.itis.covid.entries.CovidStatistic;

public interface CovidClient {
    Flux<CovidStatistic> getAll();
}
