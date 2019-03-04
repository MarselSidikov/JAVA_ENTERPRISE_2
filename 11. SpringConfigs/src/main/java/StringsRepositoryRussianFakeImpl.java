import com.google.common.collect.Lists;

import java.util.List;

/**
 * 04.03.2019
 * StringsRepositoryRussianFakeImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class StringsRepositoryRussianFakeImpl implements StringsRepository {
    @Override
    public List<String> findAll() {
        return Lists.newArrayList("Привет", "Пока", "Марсель");
    }
}
