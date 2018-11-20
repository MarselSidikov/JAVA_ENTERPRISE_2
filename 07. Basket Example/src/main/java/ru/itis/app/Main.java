package ru.itis.app;

import ru.itis.context.ApplicationContext;
import ru.itis.context.ApplicationContextPrimitiveImpl;
import ru.itis.context.Contexts;
import ru.itis.repositories.UsersRepository;

/**
 * 19.11.2018
 * Main
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Contexts.primitive();
        UsersRepository usersRepository = context.getComponent(UsersRepository.class);

    }
}
