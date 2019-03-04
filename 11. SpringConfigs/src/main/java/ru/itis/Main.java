package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 04.03.2019
 * ru.itis.Main
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis/context.xml");
        StringsService service = context.getBean("stringsService", StringsService.class);

        System.out.println(service.getRandomString());
    }
}
