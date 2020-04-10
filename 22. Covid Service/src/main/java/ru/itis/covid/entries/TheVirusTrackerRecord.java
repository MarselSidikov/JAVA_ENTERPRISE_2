package ru.itis.covid.entries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TheVirusTrackerRecord {
    @JsonProperty("countrycode")
    private String countryCode;
    private String date;
    private String recovered;
}
