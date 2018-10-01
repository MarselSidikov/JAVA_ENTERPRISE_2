package ru.itis.pizza.servlets;

import lombok.SneakyThrows;
import ru.itis.pizza.forms.UserForm;
import ru.itis.pizza.repositories.UsersRepository;
import ru.itis.pizza.repositories.UsersRepositoryConnectionImpl;
import ru.itis.pizza.services.UsersService;
import ru.itis.pizza.services.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 01.10.2018
 * SignUpServlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty007";
    private static final String URL = "jdbc:postgresql://localhost:5432/pizza_db";

    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init() throws ServletException {
        Class.forName("org.postgresql.Driver");
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UsersRepository usersRepository = new UsersRepositoryConnectionImpl(connection);
        usersService = new UsersServiceImpl(usersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Type", "text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<form method='post'>\n" +
                "\t\t<label for='email'>E-mail</label><br>\n" +
                "\t\t<input type='text' id='email' name='email' placeholder='E-mail'><br>\n" +
                "\t\t<label for='password'>Password</label><br>\n" +
                "\t\t<input type='text' id='password' name='password' placeholder='Password'><br>\n" +
                "\t\t<label for='firstName'>First Name</label><br>\n" +
                "\t\t<input type='text' id='firstName' name='firstName' placeholder='First Name'><br>\n" +
                "\t\t<label for='lastName'>Last Name</label><br>\n" +
                "\t\t<input type='text' id='lastName' name='lastName' placeholder='Last Name'><br>\n" +
                "\t\t<input type='submit' value='Sign Up'>\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        UserForm userForm = UserForm.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        usersService.signUp(userForm);
        response.sendRedirect("/signIn");
    }
}
