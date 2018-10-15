package ru.itis.pizza.context;

import lombok.SneakyThrows;
import ru.itis.pizza.repositories.UsersRepository;
import ru.itis.pizza.repositories.UsersRepositoryConnectionImpl;
import ru.itis.pizza.services.UsersService;
import ru.itis.pizza.services.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 15.10.2018
 * UsersServiceListener
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersServiceListener implements ServletContextListener {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty007";
    private static final String URL = "jdbc:postgresql://localhost:5432/pizza_db";

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent sce) {
        Class.forName("org.postgresql.Driver");
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryConnectionImpl(connection);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        ServletContext context = sce.getServletContext();
        context.setAttribute("usersService", usersService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
