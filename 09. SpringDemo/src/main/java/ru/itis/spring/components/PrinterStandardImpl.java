package ru.itis.spring.components;

/**
 * 29.10.2018
 * ru.itis.spring.components.PrinterStandardImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class PrinterStandardImpl implements Printer {

    @Override
    public void print(double sum, double amount) {
        System.out.println("Запрошено: " + sum);
        System.out.println("Осталось: " + amount);
    }
}
