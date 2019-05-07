package ru.itpark.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String value;

    public static TokenDto from(String token) {
        return TokenDto.builder()
                .value(token)
                .build();
    }
}
