package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import java.util.List;

/**
 * 11.03.2019
 * UsersController
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsersPage(ModelMap modelMap) {
        List<User> users = usersService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "users_page";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addUser(User user) {
        usersService.addUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getUsersAsJson() {
        return usersService.getAllUsers();
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> addUserAsJson(@RequestBody User user) {
        usersService.addUser(user);
        return usersService.getAllUsers();
    }


}
