package ru.itis;

/**
 * 29.10.2018
 * BadTerminalImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class BadTerminalImpl implements Terminal {

    public BadTerminalImpl(Double amount, Printer printer) {

    }
    @Override
    public double giveMoney(double sum) {
        System.out.println("НЕТ ДЕНЕГ, НО ВЫ ДЕРЖИТЕСЬ ТАМ");
        return 0;
    }
}
