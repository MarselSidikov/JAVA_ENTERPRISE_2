package ru.itis.pizza.servlets;

import lombok.SneakyThrows;
import ru.itis.pizza.forms.LoginForm;
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
 * SignInServlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

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
                "\t\t<input type='password' id='password' name='password' placeholder='Password'><br> \n" +
                "\t\t<input type='submit' value='Sign Up'>\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginForm loginForm = LoginForm.builder()
                .email(email)
                .password(password)
                .build();

        usersService.signIn(loginForm);
        PrintWriter writer = response.getWriter();
        writer.println("<h1>Ой все.</h1>");
        writer.println("<h2>Ясно.</h2>");
        writer.println("<h3>раньше ты был другим..........</h3>");
    }
}
