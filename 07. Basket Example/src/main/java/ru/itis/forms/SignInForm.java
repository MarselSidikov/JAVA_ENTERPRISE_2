package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 25.10.2018
 * SignInForm
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInForm {
    private String name;
    private String password;
}
