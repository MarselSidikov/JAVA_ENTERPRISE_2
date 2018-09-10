package ru.itis.pizza.repositories;

import lombok.SneakyThrows;
import ru.itis.pizza.mappers.RowMapper;
import ru.itis.pizza.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 03.09.2018
 * UsersRepositoryConnectionImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryConnectionImpl implements UsersRepository {

    private Connection connection;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into pizza_user(first_name, last_name, email, hash_password)" +
            "values (?, ?, ?, ?);";

    public UsersRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return null;
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
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

    @Override
    public Optional<User> findOne(Long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM pizza_user WHERE id = " + id);
            resultSet.next();
            return Optional.of(userRowMapper.rowMap(resultSet));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @SneakyThrows
    @Override
    public void save(User model) {
//        Statement statement = connection.createStatement();
//        String query = "insert into pizza_user(first_name, last_name, email, hash_password) " +
//                "values('" + model.getFirstName() + "','" +
//                model.getLastName() + "','" +
//                model.getEmail() + "','" +
//                model.getHashPassword() + "');";
//        System.out.println(query);
//        int affectedRows = statement.executeUpdate(query);
//        System.out.println(affectedRows);
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
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM pizza_user");
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User newUser = userRowMapper.rowMap(resultSet);
                users.add(newUser);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
