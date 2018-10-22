package ru.itis.pizza.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.pizza.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * 22.10.2018
 * UsersRepositoryJdbcTemplateImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_INSERT =
            "insert into pizza_user(first_name, last_name, email, hash_password) values (?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "select * from pizza_user";

    //language=SQL
    private static final String SQL_SELECT_BY_ID =
            "select * from pizza_user where id = ?";

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .email(resultSet.getString("email"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .hashPassword(resultSet.getString("hash_password"))
            .build();

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
    }

    @Override
    public void save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[] {"id"});
                    ps.setString(1, model.getFirstName());
                    ps.setString(2, model.getLastName());
                    ps.setString(3, model.getEmail());
                    ps.setString(4, model.getHashPassword());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }
}
