package ru.itis.covid.entries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CovidStatistic {
    private String dateTime;
    private String country;
    private Integer recovered;
    private String from;
}
