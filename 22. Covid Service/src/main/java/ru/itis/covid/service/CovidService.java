package ru.itis.covid.service;

import reactor.core.publisher.Flux;
import ru.itis.covid.entries.CovidStatistic;


public interface CovidService {
    Flux<CovidStatistic> getAll();
}
