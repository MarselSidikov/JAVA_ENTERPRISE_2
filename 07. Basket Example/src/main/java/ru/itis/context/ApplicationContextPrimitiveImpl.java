package ru.itis.context;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 19.11.2018
 * ApplicationContextPrimitiveImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class ApplicationContextPrimitiveImpl implements ApplicationContext {
    private static ApplicationContextPrimitiveImpl context;

    static {
        context = new ApplicationContextPrimitiveImpl();
    }

    private Map<String, Object> components;

    private ApplicationContextPrimitiveImpl() {
        components = new HashMap<>();
        components.put("dataSource", new DriverManagerDataSource());
        components.put("usersRepository", new UsersRepositoryJdbcTemplateImpl((DataSource) components.get("dataSource")));
    }

    public static ApplicationContextPrimitiveImpl getContext() {
        return context;
    }

    public <T> T getComponent(Class<T> componentClass) {
        for (Object component : components.values()) {
            if (componentClass.isAssignableFrom(component.getClass())) {
                return (T)component;
            }
        }
        return null;
    }
}
