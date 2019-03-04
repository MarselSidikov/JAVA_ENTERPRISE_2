import java.util.List;
import java.util.Random;

/**
 * 04.03.2019
 * StringsServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class StringsServiceImpl implements StringsService {

    private StringsRepository stringsRepository;

    private Random random;

    public StringsServiceImpl(StringsRepository stringsRepository) {
        this.stringsRepository = stringsRepository;
        this.random = new Random();
    }

    @Override
    public String getRandomString() {
        List<String> allStrings = stringsRepository.findAll();
        return allStrings.get(random.nextInt(allStrings.size()));
    }
}
