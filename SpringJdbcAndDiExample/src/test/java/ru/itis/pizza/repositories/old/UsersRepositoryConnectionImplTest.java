package ru.itis.pizza.repositories.old;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import ru.itis.pizza.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * 22.10.2018
 * UsersRepositoryConnectionImplTest
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryConnectionImplTest {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty007";
    private static final String URL = "jdbc:postgresql://localhost:5432/pizza_db";

    private UsersRepositoryConnectionImpl userRepository;

    private static final User EXPECTED_USER = User.builder()
            .id(1L)
            .firstName("Sidikov")
            .lastName("Marsel")
            .email("sidikov.marsel@gmail.com")
            .build();

    private static final List<User> EXPECTED_USERS  =
            Lists.newArrayList(User.builder()
                    .id(1L)
                    .firstName("Sidikov")
                    .lastName("Marsel")
                    .email("sidikov.marsel@gmail.com")
                    .build(),
                    User.builder()
                            .id(2L)
                            .firstName("Shagieva")
                            .lastName("Daria")
                            .email("shagieva.daria@gmail.com")
                            .build());
    @Before
    public void setUp() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        userRepository = new UsersRepositoryConnectionImpl(connection);
    }

    @Test
    public void findOne() {
        Optional<User> actualOptional = userRepository.findOne(1L);
        if (!actualOptional.isPresent()) {
            fail("Return object is null");
        } else {
            User actual = actualOptional.get();
            assertEquals(EXPECTED_USER, actual);
        }
    }

    @Test
    public void save() {
        User user = User.builder()
                .email("demo@demo.com")
                .firstName("Demo")
                .lastName("Demov")
                .build();

        userRepository.save(user);
    }

    @Test
    public void testFindAll() {
        List<User> actual = userRepository.findAll();
        assertArrayEquals(EXPECTED_USERS.toArray(), actual.toArray());
    }
}