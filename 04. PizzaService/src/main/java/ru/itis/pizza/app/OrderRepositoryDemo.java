package ru.itis.pizza.app;

import lombok.SneakyThrows;
import ru.itis.pizza.models.Order;
import ru.itis.pizza.models.User;
import ru.itis.pizza.repositories.OrderRepository;
import ru.itis.pizza.repositories.OrderRepositoryConnectionImpl;
import ru.itis.pizza.repositories.UsersRepository;
import ru.itis.pizza.repositories.UsersRepositoryConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

/**
 * 10.09.2018
 * OrderRepositoryDemo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class OrderRepositoryDemo {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty007";
    private static final String URL = "jdbc:postgresql://localhost:5432/pizza_db";

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        OrderRepository orderRepository = new OrderRepositoryConnectionImpl(connection);
        orderRepository.findOne(5L).ifPresent(System.out::println);
//        Optional<Order> orderOptional = orderRepository.findOne(5L);
//        if (orderOptional.isPresent()) {
//            System.out.println(orderOptional.get());
//        }

    }
}
