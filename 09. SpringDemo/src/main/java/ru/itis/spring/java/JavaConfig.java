package ru.itis.spring.java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.itis.spring.components.*;

/**
 * 10.12.2018
 * JavaConfig
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Configuration
@PropertySource("classpath:application.properties")
public class JavaConfig {

    @Value("${printer.name}")
    private String printerName;

    @Value("${printer.prefix}")
    private String printerPrefix;

    @Value("${terminal.amount}")
    private Double terminalAmount;

    @Bean
    public Terminal terminal() {
        return new BadTerminalImpl(terminalAmount, printer());
    }

    @Bean
    public Terminal plainTerminal() {
        return new PlainTerminal(terminalAmount, printer());
    }

    @Bean
    public Printer printer() {
        PrinterRedImpl printerRed = new PrinterRedImpl(printerName);
        printerRed.setPrefix(printerPrefix);
        return printerRed;
    }
}
