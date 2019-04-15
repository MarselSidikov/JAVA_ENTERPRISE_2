package ru.itis.springbootapp.forms;

import lombok.Data;

@Data
public class SignUpForm {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
