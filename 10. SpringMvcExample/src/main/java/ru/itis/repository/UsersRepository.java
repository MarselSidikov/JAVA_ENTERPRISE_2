package ru.itis.repository;

import ru.itis.models.User;

import java.util.Optional;

/**
 * 22.10.2018
 * UsersRepository
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByName(String name);
}
