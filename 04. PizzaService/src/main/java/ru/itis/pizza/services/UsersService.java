package ru.itis.pizza.services;

import ru.itis.pizza.forms.LoginForm;
import ru.itis.pizza.forms.UserForm;

/**
 * 01.10.2018
 * UsersService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersService {
    void signUp(UserForm userForm);

    void signIn(LoginForm loginForm);
}
