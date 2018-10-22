package ru.itis.pizza.repositories;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.pizza.models.User;

import static org.junit.Assert.*;

/**
 * 22.10.2018
 * UsersRepositoryJdbcTemplateImplTest
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcTemplateImplTest {

    UsersRepositoryJdbcTemplateImpl usersRepository;

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty007";
    private static final String URL = "jdbc:postgresql://localhost:5432/pizza_db";

    @Before
    public void setUp() throws Exception {
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
    }

    @Test
    public void findOne() {
        System.out.println(usersRepository.findOne(1L));
    }

    @Test
    public void save() {
        User beforeSave = User.builder()
                .firstName("Слава Cataline")
                .lastName("Сервлетам Слава!")
                .email("warnikam@net.net")
                .hashPassword("UA")
                .build();

        usersRepository.save(beforeSave);
        System.out.println(beforeSave);
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
        System.out.println(usersRepository.findAll());
    }
}