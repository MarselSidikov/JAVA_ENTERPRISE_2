package ru.itis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 16.10.2018
 * HumansGenerator
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
// класс, генерирующий людей
public class HumansGenerator {
    // список имен в виде константного массива
    private static final String FIRST_NAMES[] = {"Артем",
            "Ляйсан", "Ильгам", "Виктор", "Дмитрий",
            "Александр", "Рузия", "Карина", "Марина",
            "Арина", "Жан", "Клод", "ВанДам", "Никита"};

    // список фамилий в виде константного массива
    private static final String LAST_NAMES[] = {"Сидиков", "Хасанов",
            "Файдрахманова", "Сулейманова", "Вахрушев", "Воробьев", "Ахматдинов", "Анцырев",
            "Галустян", "Вок", "Михалков"};

    // метод, генерирующий список людей
    // возвращает список на основе массива ArrayList
    // на вход принимает количество сгенерированных людей
    public ArrayList<Human> generateHumans(int count) {
        // создаем пустой список
        ArrayList<Human> humans = new ArrayList<>();
        // создаем объект, который умеет генерировать числа
        Random random = new Random();
        // запускаем цикл
        for (int i = 0; i < count; i++) {
            // получаем из массива случайное имя
            String firstName = FIRST_NAMES[random.nextInt(14)];
            // получаем случайную фамилию
            String lastName = LAST_NAMES[random.nextInt(11)];
            // генерируем случайный возраст
            int age = random.nextInt(30);
            // создаем объект человека на основе полученных данных
            Human human = new Human(firstName, lastName, age);
            // добавляем человека в список
            humans.add(human);
        }
        // возвращаем результат
        return humans;
    }
}
