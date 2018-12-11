package ru.itis.spring.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.spring.components.Printer;
import ru.itis.spring.components.Terminal;

/**
 * 10.12.2018
 * Main
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);

        Terminal terminal = context.getBean("plainTerminal", Terminal.class);
        terminal.giveMoney(50);
    }
}
