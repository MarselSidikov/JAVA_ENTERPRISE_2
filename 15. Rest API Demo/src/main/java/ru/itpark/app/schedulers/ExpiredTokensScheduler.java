package ru.itpark.app.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.itpark.app.repositories.TokensRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class ExpiredTokensScheduler {

    @Autowired
    private TokensRepository tokensRepository;

    @Scheduled(cron = "*/10 * * * * *")
    @Transactional
    public void removeExpiredTokens() {
        tokensRepository.deleteTokensByExpiredDateTimeBefore(LocalDateTime.now());
    }

}
