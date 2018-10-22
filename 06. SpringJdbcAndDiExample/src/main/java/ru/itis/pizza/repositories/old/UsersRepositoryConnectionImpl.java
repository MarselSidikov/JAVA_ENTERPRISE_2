package ru.itis.pizza.repositories.old;

import ru.itis.pizza.models.User;
import ru.itis.pizza.repositories.UsersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    //language=SQL
    private static final String SQL_INSERT_QUERY =
            "insert into pizza_user(first_name, last_name, email, hash_password) values (?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "select * from pizza_user";

    //language=SQL
    private static final String SQL_FIND_BY_ID =
            "select * from pizza_user where id = ?";

    private RowMapper<User> rowMapper = resultSet -> {
        try {
            return User.builder()
                    .id(resultSet.getLong("id"))
                    .email(resultSet.getString("email"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .hashPassword(resultSet.getString("hash_password"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    public UsersRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<User> findOne(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            User result = null;
            while (resultSet.next()) {
                result = rowMapper.rowMap(resultSet);
            }
            return Optional.ofNullable(result);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(User model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.setString(3, model.getEmail());
            statement.setString(4, model.getHashPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                model.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> findAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User newUser = rowMapper.rowMap(resultSet);
                users.add(newUser);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
