package ru.itis.springbootapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springbootapp.forms.UserForm;
import ru.itis.springbootapp.models.User;
import ru.itis.springbootapp.repositories.UsersRepository;

import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public String getUsersPage() {
        return "users";
    }

    @PostMapping("/users/{user-id}")
    @ResponseBody
    public ResponseEntity<Object> updateUser(@PathVariable("user-id") Long userId, UserForm userForm) {
        Optional<User> userCandidate = usersRepository.findById(userId);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getFirstName());
            usersRepository.save(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
