package ru.itis.services;

import org.springframework.security.core.userdetails.User;
import ru.itis.dto.UserDto;
import ru.itis.forms.SignInForm;
import ru.itis.forms.SignUpForm;

import javax.swing.text.html.Option;
import java.util.List;
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

    List<UserDto> findAll();

    Optional<String> signIn(SignInForm form);

    boolean isExistByCookie(String cookieValue);
}
