package ru.itpark.app.services;

import ru.itpark.app.dto.LoginDto;
import ru.itpark.app.dto.TokenDto;

public interface LoginService {
    TokenDto login(LoginDto loginData);
}
