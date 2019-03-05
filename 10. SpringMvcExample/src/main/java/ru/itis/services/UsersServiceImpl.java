package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.repository.UsersRepository;
import ru.itis.models.User;

import java.util.List;

/**
 * 09.10.2017
 * UsersServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }
}
