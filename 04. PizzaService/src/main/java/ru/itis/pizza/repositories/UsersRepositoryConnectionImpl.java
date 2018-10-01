package ru.itis.pizza.repositories;

import lombok.SneakyThrows;
import ru.itis.pizza.mappers.RowMapper;
import ru.itis.pizza.models.Order;
import ru.itis.pizza.models.User;

import java.sql.*;
import java.util.*;

/**
 * 03.09.2018
 * UsersRepositoryConnectionImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryConnectionImpl implements UsersRepository {

    private Connection connection;

    // Карта, которая хранит единственного пользователя
    // и список его заказов
    private Map<User, List<Order>> userWithOrdersMap;

    // Карта, которая хранит id-шники всех
    // пользователей и объекты самих пользователей, внутри
    // которых уже хранятся заказы
    private Map<Long, User> userIdWithOrdersMap;

    // временная переменная, которая хранит текущего пользователя
    private User theOnlyUser;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into pizza_user(first_name, last_name, email, hash_password)" +
            "values (?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_SELECT_USER_WITH_ORDERS_BY_ID =
            "select order_table.id as order_id, * " +
                    "from pizza_user " +
                    "       join order_table on pizza_user.id = order_table.client_id " +
                    "where pizza_user.id = ?;";

    //language=SQL
    private static final String SQL_SELECT_USERS_WITH_ORDERS =
            "select order_table.id as order_id, * " +
                    "from pizza_user " +
                    "       join order_table on pizza_user.id = order_table.client_id " +
                    "order by pizza_user.id;";

    public UsersRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return null;
    }

    // просто RowMapper, который преобразует строку
    // resultSet в один объект пользователя без дополнительной информации
    private RowMapper<User> userWithoutOrdersRowMapper = new RowMapper<User>() {
        @Override
        @SneakyThrows
        public User rowMap(ResultSet resultSet) {
            return User.builder()
                    .email(resultSet.getString("email"))
                    .hashPassword(resultSet.getString("hash_password"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .id(resultSet.getLong("id"))
                    .build();
        }
    };

    // RowMapper, который работает так, что помимо информации
    // о пользователе вытаскивает информацию
    // по заказам
    private RowMapper<User> userWithOrdersForOneUserRowMapper = new RowMapper<User>() {
        @Override
        @SneakyThrows
        public User rowMap(ResultSet resultSet) {
            // если пользователь еще не был добавлен
            if (userWithOrdersMap.size() == 0) {
                // отображаем строку resultSet в объект
                User newUser = userWithoutOrdersRowMapper.rowMap(resultSet);
                // кладем этот объект в мап
                userWithOrdersMap.put(newUser, new ArrayList<>());
                // запоминаем его (он единственный)
                theOnlyUser = newUser;
            }
            // вытаскиваем заказ
            Order order = Order.builder()
                    .id(resultSet.getLong("order_id"))
                    .address(resultSet.getString("address"))
                    .dateTime(resultSet.getTimestamp("date").toLocalDateTime())
                    .client(theOnlyUser)
                    .build();
            // кладем в мап этому пользователю этот заказ
            userWithOrdersMap.get(theOnlyUser).add(order);
            return theOnlyUser;
        }
    };

    // позволяет обрабатывать массив строк пользователей
    // с их заказами
    private RowMapper<User> userWithOrdersRowMapper = new RowMapper<User>() {
        @Override
        @SneakyThrows
        public User rowMap(ResultSet resultSet) {
            // смотрим id текущего пользователя
            Long currentUserId = resultSet.getLong("id");
            // если пользователь еще не был добавлен
            if (!userIdWithOrdersMap.containsKey(currentUserId)) {
                // отображаем строку resultSet в объект
                User newUser = userWithoutOrdersRowMapper.rowMap(resultSet);
                // создаю пользователю пустой список заказов
                newUser.setOrders(new ArrayList<>());
                // кладу его в мап
                userIdWithOrdersMap.put(currentUserId, newUser);
            }
            // вытаскиваем заказ
            Order order = Order.builder()
                    .id(resultSet.getLong("order_id"))
                    .address(resultSet.getString("address"))
                    .dateTime(resultSet.getTimestamp("date").toLocalDateTime())
                    .client(userIdWithOrdersMap.get(currentUserId))
                    .build();
            // получаю текущего пользователя по его id
            // получаю список его заказов
            // добавляю заказ
            User currentUser = userIdWithOrdersMap.get(currentUserId);
            List<Order> ordersOfUser = currentUser.getOrders();
            ordersOfUser.add(order);
            return currentUser;
        }
    };

    @Override
    @SneakyThrows
    public Optional<User> findOne(Long id) {
        userWithOrdersMap = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_WITH_ORDERS_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            userWithOrdersForOneUserRowMapper.rowMap(resultSet);
        }
        theOnlyUser.setOrders(userWithOrdersMap.get(theOnlyUser));
        User result = theOnlyUser;
        theOnlyUser = null;
        userWithOrdersMap.clear();
        return Optional.of(result);
    }

    @SneakyThrows
    @Override
    public void save(User model) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, model.getFirstName());
        statement.setString(2, model.getLastName());
        statement.setString(3, model.getEmail());
        statement.setString(4, model.getHashPassword());
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setId(resultSet.getLong("id"));
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    @SneakyThrows
    public List<User> findAll() {
        userIdWithOrdersMap = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_WITH_ORDERS);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            userWithOrdersRowMapper.rowMap(resultSet);
        }
        return new ArrayList<>(userIdWithOrdersMap.values());
    }
}
