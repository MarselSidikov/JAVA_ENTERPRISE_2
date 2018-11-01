package ru.itis;

import javax.management.ReflectionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 29.10.2018
 * ApplicationContext
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class ApplicationContext {

    private Map<String, Object> components;
    private Properties properties;

    private static ApplicationContext context;

    private ApplicationContext() {
        components = new HashMap<>();
        properties = new Properties();
        try {
            properties.load(new FileInputStream("resources\\application.properties"));

            Class<Printer> printerClass = (Class<Printer>) Class.forName(properties.getProperty("printer.class"));
            Class<Terminal> terminalClass = (Class<Terminal>) Class.forName(properties.getProperty("terminal.class"));

            Double amount = Double.parseDouble(properties.getProperty("terminal.amount"));
            Printer printer = printerClass.newInstance();

            Constructor<Terminal> terminalConstructor = terminalClass.getConstructor(Double.class, Printer.class);

            Terminal terminal = terminalConstructor.newInstance(amount, printer);

            components.put("printer", printer);
            components.put("terminal", terminal);
        } catch (IOException | ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    static {
        context = new ApplicationContext();
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public <T> T getComponent(Class<T> aClass, String componentName) {
        Object component =  components.get(componentName);
        return (T)component;
    }
}
