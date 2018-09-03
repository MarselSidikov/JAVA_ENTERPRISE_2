package ru.itis.pizza.app;

import lombok.SneakyThrows;
import ru.itis.pizza.models.User;
import ru.itis.pizza.repositories.UsersRepository;
import ru.itis.pizza.repositories.UsersRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 03.09.2018
 * UsersRepositoryDemo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryDemo {

    // данные для подключения
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty007";
    private static final String URL = "jdbc:postgresql://localhost:5432/pizza_db";

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryImpl(connection);
        User user = usersRepository.findOne(1L);
        System.out.println(user);
        System.out.println(usersRepository.findAll());
    }
}
