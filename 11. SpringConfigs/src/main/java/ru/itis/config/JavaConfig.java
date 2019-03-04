package ru.itis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itis.StringsRepository;
import ru.itis.StringsRepositoryEnglishFakeImpl;
import ru.itis.StringsRepositoryRussianFakeImpl;

/**
 * 04.03.2019
 * AnnotationConfig
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Configuration
public class JavaConfig {

    @Bean
    public StringsRepository stringsRussianRepository() {
        return new StringsRepositoryRussianFakeImpl();
    }

    @Bean
    public StringsRepository stringsEnglishRepository() {
        return new StringsRepositoryEnglishFakeImpl();
    }
}
