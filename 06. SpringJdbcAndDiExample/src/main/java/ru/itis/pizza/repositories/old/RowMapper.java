package ru.itis.pizza.repositories.old;

import java.sql.ResultSet;

/**
 * 03.09.2018
 * RowMapper
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface RowMapper<T> {
    T rowMap(ResultSet resultSet);
}
