package ru.itis.pizza.servlets;

import lombok.SneakyThrows;
import ru.itis.pizza.forms.LoginForm;
import ru.itis.pizza.repositories.UsersRepository;
import ru.itis.pizza.repositories.UsersRepositoryConnectionImpl;
import ru.itis.pizza.services.UsersService;
import ru.itis.pizza.services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        usersService = (UsersService)context.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signIn.jsp").forward(request, response);
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
