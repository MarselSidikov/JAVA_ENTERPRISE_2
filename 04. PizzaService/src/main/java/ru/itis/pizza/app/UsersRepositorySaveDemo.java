package ru.itis.pizza.app;

import lombok.SneakyThrows;
import ru.itis.pizza.models.User;
import ru.itis.pizza.repositories.UsersRepository;
import ru.itis.pizza.repositories.UsersRepositoryConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 10.09.2018
 * UsersRepositorySaveDemo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositorySaveDemo {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty007";
    private static final String URL = "jdbc:postgresql://localhost:5432/pizza_db";

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryConnectionImpl(connection);
        User maxim = User.builder()
                .firstName("Максим")
                .lastName("Поздеев")
                .email("maximka@mail.ru")
                .hashPassword("');DROP TABLE pizza; INSERT INTO pizza_user(first_name) values('temp")
                .build();
        usersRepository.save(maxim);

    }
}
