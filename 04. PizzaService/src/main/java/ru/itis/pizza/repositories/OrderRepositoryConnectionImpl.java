package ru.itis.pizza.repositories;

import lombok.SneakyThrows;
import ru.itis.pizza.models.Order;
import ru.itis.pizza.models.Pizza;
import ru.itis.pizza.models.User;

import java.security.acl.Owner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 10.09.2018
 * OrderRepositoryConnectionImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class OrderRepositoryConnectionImpl implements OrderRepository {

    private Connection connection;

    //language=SQL
    private static final String SQL_SELECT_ORDER_WITH_PIZZAS = "select * " +
            "from order_table" +
            "       join order_pizza o on order_table.id = o.order_id " +
            "       join pizza p on o.pizza_id = p.id " +
            "       join pizza_user user2 on order_table.client_id = user2.id " +
            "where order_table.id = ?;";

    public OrderRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    @Override
    public Optional<Order> findOne(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_WITH_PIZZAS);
        preparedStatement.setLong(1, id);
        if (preparedStatement.execute()) {
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Pizza> pizzas = new ArrayList<>();
            Order order = null;
            while (resultSet.next()) {
                if (order == null) {
                    order = Order.builder()
                            .id(resultSet.getLong("id"))
                            .address(resultSet.getString("address"))
                            .dateTime(resultSet.getObject("date", LocalDateTime.class))
                            .build();
                    User client = User.builder()
                            .id(resultSet.getLong("client_id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .hashPassword(resultSet.getString("hash_password"))
                            .build();
                    order.setClient(client);
                }
                Pizza pizza = Pizza.builder()
                        .id(resultSet.getLong("pizza_id"))
                        .price(resultSet.getDouble("price"))
                        .name(resultSet.getString("name"))
                        .build();
                pizzas.add(pizza);
            }
            if (order != null) {
                order.setPizzas(pizzas);
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(Order model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Order> findAll() {
        return null;
    }
}
