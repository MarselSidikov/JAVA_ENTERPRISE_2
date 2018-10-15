package ru.itis.pizza.servlets;

import lombok.SneakyThrows;
import ru.itis.pizza.forms.UserForm;
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
import java.util.Map;

/**
 * 01.10.2018
 * SignUpServlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        if (lang == null) {
            lang = "En";
        }
        Map<String, String> locale = (Map<String, String>) request.getServletContext().getAttribute("locale" + lang);
        request.setAttribute("locale", locale);
        request.getRequestDispatcher("/jsp/signUp.jsp").forward(request, response);
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
