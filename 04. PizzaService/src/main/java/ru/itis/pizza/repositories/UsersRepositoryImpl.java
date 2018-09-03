package ru.itis.pizza.repositories;

import lombok.SneakyThrows;
import ru.itis.pizza.mappers.RowMapper;
import ru.itis.pizza.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 03.09.2018
 * UsersRepositoryImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryImpl implements UsersRepository {

    private Connection connection;

    public UsersRepositoryImpl(Connection connection) {
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
    public User findOne(Long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM pizza_user WHERE id = " + id);
            resultSet.next();
            return userRowMapper.rowMap(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User model) {

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
