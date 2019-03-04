package ru.itis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Random;

/**
 * 04.03.2019
 * ru.itis.StringsServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class StringsServiceImpl implements StringsService {

    @Autowired
    @Qualifier("stringsEnglishRepository")
    private StringsRepository stringsRepository;

    private Random random;

    public StringsServiceImpl() {
        this.random = new Random();
    }

    @Override
    public String getRandomString() {
        List<String> allStrings = stringsRepository.findAll();
        return allStrings.get(random.nextInt(allStrings.size()));
    }
}
