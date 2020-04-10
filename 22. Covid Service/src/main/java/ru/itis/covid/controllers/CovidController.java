package ru.itis.covid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.covid.entries.CovidStatistic;
import ru.itis.covid.service.CovidService;

import java.util.List;

@RestController
@RequestMapping("/covid-management")
public class CovidController {

    @Autowired
    private CovidService covidService;

    @GetMapping(value = "/records", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CovidStatistic> getAll() {
        return covidService.getAll();
    }
}
