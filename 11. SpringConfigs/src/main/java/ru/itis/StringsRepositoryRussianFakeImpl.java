package ru.itis;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 04.03.2019
 * ru.itis.StringsRepositoryRussianFakeImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component("stringsRepositoryRussian")
public class StringsRepositoryRussianFakeImpl implements StringsRepository {
    @Override
    public List<String> findAll() {
        return Lists.newArrayList("Привет", "Пока", "Марсель");
    }
}
