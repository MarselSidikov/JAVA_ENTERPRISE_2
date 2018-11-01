package ru.itis.services;

import ru.itis.forms.SignInForm;
import ru.itis.forms.SignUpForm;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * 25.10.2018
 * UsersService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersService {
    void signUp(SignUpForm form);

    Optional<String> signIn(SignInForm form);

    boolean isExistByCookie(String cookieValue);
}
