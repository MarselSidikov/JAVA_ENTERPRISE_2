package ru.itis.spring.components;

/**
 * 29.10.2018
 * ru.itis.spring.components.PrinterRedImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class PrinterRedImpl implements Printer {

    private String prefix;
    private String name;

    public PrinterRedImpl(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(double sum, double amount) {
        System.out.println(name);
        System.out.println(prefix);
        System.err.println("ЗАПРОШЕНО: " + sum);
        System.err.println("СКАЗАЛИ ЖЕ ЧТО ОСТАЛОСЬ " + amount + " рублей, нищеброд!");
    }
}
