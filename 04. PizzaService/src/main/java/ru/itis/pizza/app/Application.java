package ru.itis.pizza.app;

import java.sql.*;

/**
 * 03.09.2018
 * Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Application {
    // данные для подключения
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty007";
    private static final String URL = "jdbc:postgresql://localhost:5432/pizza_db";

    public static void main(String[] args) {
        try {
            // подключились к базе данных
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // создали "Выражение"
            Statement statement = connection.createStatement();
            // выполнили запрос к базе данных
            // и передали указатель на первую строку результата
            // в java-код, а точнее - в объект класса
            // resultSet
            ResultSet resultSet =
                    statement.executeQuery("SELECT id, first_name from pizza_user");
            // функция next() переводит указатель дальше,
            // и одновременно возвращает true или false,
            // в зависимости от того, есть дальше еще строки или нет
            while (resultSet.next()) {
                // вывели на экран строку "строкового" типа с названием
                // first_name
                System.out.println(resultSet.getLong("id") + " " + resultSet.getString("first_name"));
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
