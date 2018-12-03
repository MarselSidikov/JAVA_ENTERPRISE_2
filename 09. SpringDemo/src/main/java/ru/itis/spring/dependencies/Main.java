package ru.itis.spring.dependencies;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.spring.components.Printer;
import ru.itis.spring.components.Terminal;

/**
 * 03.12.2018
 * Main
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

        Terminal terminal = context.getBean(Terminal.class);

        terminal.giveMoney(200);
    }
}
