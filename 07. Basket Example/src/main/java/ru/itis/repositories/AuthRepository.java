package ru.itis.repositories;

import ru.itis.models.Auth;

import java.util.Optional;

/**
 * 25.10.2018
 * AuthRepository
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface AuthRepository extends CrudRepository<Auth> {
    Optional<Auth> findByCookieValue(String cookieValue);
}
