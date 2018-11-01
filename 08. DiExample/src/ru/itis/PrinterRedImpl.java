package ru.itis;

/**
 * 29.10.2018
 * PrinterRedImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class PrinterRedImpl implements Printer {
    @Override
    public void print(double sum, double amount) {
        System.err.println("ЗАПРОШЕНО: " + sum);
        System.err.println("СКАЗАЛИ ЖЕ ЧТО ОСТАЛОСЬ " + amount + " рублей, нищеброд!");
    }
}
