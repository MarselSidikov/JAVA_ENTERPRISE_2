package ru.itis.pizza.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.pizza.forms.LoginForm;
import ru.itis.pizza.forms.UserForm;
import ru.itis.pizza.models.User;
import ru.itis.pizza.repositories.UsersRepository;

import java.util.Optional;

/**
 * 01.10.2018
 * UsersServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private PasswordEncoder encoder;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .hashPassword(encoder.encode(userForm.getPassword()))
                .build();
        usersRepository.save(user);
    }

    @Override
    public void signIn(LoginForm loginForm) {
        Optional<User> userOptional = usersRepository.findOneByEmail(loginForm.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!encoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                throw new IllegalArgumentException("Wrong password or email");
            }
        } else throw new IllegalArgumentException("Wrong password or email");
    }
}
