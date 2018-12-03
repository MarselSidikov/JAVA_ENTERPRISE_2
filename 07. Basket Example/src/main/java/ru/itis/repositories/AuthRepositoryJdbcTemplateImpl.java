package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Auth;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * 25.10.2018
 * AuthRepositoryJdbcTemplateImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class AuthRepositoryJdbcTemplateImpl implements AuthRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT =
            "insert into auth(user_id, cookie_value) values (?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_COOKIE_VALUE =
            "select * from auth where cookie_value = ?";

    public AuthRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Auth> authRowMapper = new RowMapper<Auth>() {
        @Override
        public Auth mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Auth.builder()
                    .id(rs.getLong("id"))
                    .cookieValue(rs.getString("cookie_value"))
                    .build();
        }
    };

    @Override
    public List<Auth> findAll() {
        return null;
    }

    @Override
    public Optional<Auth> find(Long id) {
        return null;
    }

    @Override
    public void save(Auth model) {
        jdbcTemplate.update(SQL_INSERT, model.getUser().getId(), model.getCookieValue());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Auth model) {

    }

    @Override
    public Optional<Auth> findByCookieValue(String cookieValue) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE_VALUE, authRowMapper, cookieValue));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
