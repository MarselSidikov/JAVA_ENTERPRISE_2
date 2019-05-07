package ru.itpark.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itpark.app.dto.LoginDto;
import ru.itpark.app.dto.TokenDto;
import ru.itpark.app.services.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginData) {
        return ResponseEntity.ok(service.login(loginData));
    }
}
