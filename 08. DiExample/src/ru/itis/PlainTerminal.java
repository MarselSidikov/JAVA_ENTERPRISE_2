package ru.itis;

/**
 * 29.10.2018
 * PlainTerminal
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class PlainTerminal implements Terminal {
    private double amount;

    private Printer printer;

    public PlainTerminal(Double amount, Printer printer) {
        this.amount = amount;
        this.printer = printer;
    }

    public double giveMoney(double sum) {
        if (sum > amount) {
            throw new IllegalArgumentException("No money no honey");
        } else {
            amount = amount - sum;
            printer.print(sum, amount);
            return sum;
        }
    }
}
