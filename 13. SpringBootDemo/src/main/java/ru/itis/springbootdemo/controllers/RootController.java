package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.List;

@Controller
public class RootController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/")
    public String getRootPage(ModelMap model) {
        List<User> users = usersRepository.findAll();
        model.addAttribute("user", users.get(0));
        return "index";
    }
}
