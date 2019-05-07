package ru.itpark.app.services;

import ru.itpark.app.models.User;

import java.util.List;

public interface UsersService {
    List<User> getUsers(Boolean sort, String by, Boolean desc);
    List<User> getUsersWithSearch(String query);
}
