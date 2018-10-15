package ru.itis.pizza.localization;

import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 15.10.2018
 * Localizations
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Localizations {
    @SneakyThrows
    public static Map<String, String> loadLocalization(String locale) {
        Scanner scanner = new Scanner(new File("C:\\Users\\admin.WIN-IPM3OA3VQNQ\\Desktop\\Java\\JAVA_ENTERPRISE_2\\04. PizzaService\\src\\main\\resources\\messages\\messages_" + locale + ".properties"));

        Map<String, String> localeMap = new HashMap<>();

        while (scanner.hasNext()) {
            String currentValue = scanner.nextLine();
            String localeValue[] = currentValue.split("=");
            localeMap.put(localeValue[0], localeValue[1]);
        }

        return localeMap;
    }
}
