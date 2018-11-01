package ru.itis.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.AuthRepository;
import ru.itis.repositories.AuthRepositoryImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 29.10.2018
 * ComponentsListener
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class ComponentsListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("qwerty007");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/shop");
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        AuthRepository authRepository = new AuthRepositoryImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository, authRepository);
        sce.getServletContext().setAttribute("usersService", usersService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
