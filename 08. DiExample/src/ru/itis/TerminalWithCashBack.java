package ru.itis;

/**
 * 29.10.2018
 * TerminalWithCashBack
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class TerminalWithCashBack implements Terminal {
    private final int CASH_BACK_PERCENT = 5;

    private double amount;

    private Printer printer;

    public TerminalWithCashBack(Double amount, Printer printer) {
        this.amount = amount;
        this.printer = printer;
    }

    public double giveMoney(double sum) {
        double newSum = sum + sum * 0.01 * CASH_BACK_PERCENT;
        if (newSum > amount) {
            throw new IllegalArgumentException("No money no honey");
        } else {
            amount = amount - newSum;
            printer.print(newSum, amount);
            return newSum;
        }
    }
}
