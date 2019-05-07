package ru.itpark.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.app.models.User;
import ru.itpark.app.repositories.UsersRepository;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getUsers(Boolean sort, String by, Boolean desc) {
        if (sort == null || !sort) {
            return usersRepository.findAll();
        } else {
            if (by != null) {
                switch (by) {
                    case "age":
                        if (desc == null || !desc) {
                            return usersRepository.findAllByOrderByAge();
                        } else {
                            return usersRepository.findAllByOrderByAgeDesc();
                        }
                    case "id":
                        if (desc == null || !desc) {
                            return usersRepository.findAllByOrderById();
                        } else {
                            return usersRepository.findAllByOrderByIdDesc();
                        }

                }
            } return usersRepository.findAll();
        }
    }

    @Override
    public List<User> getUsersWithSearch(String query) {
        return usersRepository.findAllByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(query, query);
    }
}
