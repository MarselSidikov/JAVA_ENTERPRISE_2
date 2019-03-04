package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.config.AppAnnotationConfig;

/**
 * 04.03.2019
 * ru.itis.Main
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppAnnotationConfig.class);
        StringsService service = context.getBean(StringsService.class);

        System.out.println(service.getRandomString());
    }
}
