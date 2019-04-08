package ru.itis.springbootdemo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryJdbcTemplateImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;
}
