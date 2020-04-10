package ru.itis.covid.entries;

import lombok.Data;

import java.util.List;

@Data
public class TheVirusTrackerResponse {
    private List<TheVirusTrackerRecord> data;
}
