package ru.itis;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 16.10.2018
 * MainGenerator
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class MainGenerator {
    public static void main(String[] args) {
        // создали объект генератора людей
        HumansGenerator generator = new HumansGenerator();
        // сгенерировали необходимое количество людей
        ArrayList<Human> humans = generator.generateHumans(10000);
        // Карта, Словарь, Map
        // как массив, только вместо индекса может
        // быть любой тип, и вместо значения тоже любой тип
        // key[Ильгам Хасанов] -> value[10]
        HashMap<String, Integer> names = new HashMap<>();
        // идем по всему списку
        for (Human human : humans) {
            // берем полное имя текущего человека
            String name = human.getFirstName() + " " + human.getLastName();
            // если раньше не встречали
            if (!names.containsKey(name)) {
                // кладем это имя с едичным значением
                names.put(name, 1);
            } else {
                // увеличиваем то значение, которое было
                names.put(name, names.get(name) + 1);
            }
        }

//        for (Map.Entry<String, Integer> entry : names.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

        // множество пар Ключ/Значение
        Set<Map.Entry<String, Integer>> entries = names.entrySet();
        // массив пар
        Map.Entry<String, Integer> entriesArray[] =
                new Map.Entry[entries.size()];
        // сконвертировали множество в массив
        entries.toArray(entriesArray);
        // сортировка пузырьком
        for (int i = entriesArray.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (entriesArray[j].getValue() < entriesArray[j + 1].getValue()) {
                    Map.Entry<String, Integer> temp = entriesArray[j];
                    entriesArray[j] = entriesArray[j + 1];
                    entriesArray[j + 1] = temp;
                }
            }
        }

        for (Map.Entry<String, Integer> entry : entriesArray) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
