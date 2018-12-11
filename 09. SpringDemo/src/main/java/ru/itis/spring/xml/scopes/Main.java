package ru.itis.spring.xml.scopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.spring.components.PrinterRedImpl;

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

        PrinterRedImpl printerRed = (PrinterRedImpl)context.getBean("printer");
        printerRed.print(100, 50);

        printerRed.setPrefix("НОВЫЙ ПРЕФИКС");
        printerRed.print(100, 50);

        PrinterRedImpl otherBean = (PrinterRedImpl)context.getBean("printer");
        otherBean.print(100, 60);
    }
}
