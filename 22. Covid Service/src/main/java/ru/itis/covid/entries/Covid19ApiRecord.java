package ru.itis.covid.entries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Covid19ApiRecord {
    @JsonProperty("CountryCode")
    private String countryCode;

    @JsonProperty("Recovered")
    private Integer recovered;

    @JsonProperty("Date")
    private String date;

}
