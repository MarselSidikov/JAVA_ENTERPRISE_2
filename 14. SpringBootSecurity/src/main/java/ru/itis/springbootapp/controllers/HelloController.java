package ru.itis.springbootapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springbootapp.models.User;
import ru.itis.springbootapp.security.UserDetailsImpl;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String getHello(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        System.out.println(user);
        return "Hello";
    }
}
