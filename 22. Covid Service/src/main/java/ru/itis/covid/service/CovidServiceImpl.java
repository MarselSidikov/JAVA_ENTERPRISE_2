package ru.itis.covid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.itis.covid.clients.CovidClient;
import ru.itis.covid.entries.CovidStatistic;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidServiceImpl implements CovidService {

    @Autowired
    private List<CovidClient> clients;

    @Override
    public Flux<CovidStatistic> getAll() {
        List<Flux<CovidStatistic>> fluxes =  clients.stream().map(this::getAll).collect(Collectors.toList());
        return Flux.merge(fluxes);
    }

    private Flux<CovidStatistic> getAll(CovidClient client) {
        return client.getAll().subscribeOn(Schedulers.elastic());
    }
}
