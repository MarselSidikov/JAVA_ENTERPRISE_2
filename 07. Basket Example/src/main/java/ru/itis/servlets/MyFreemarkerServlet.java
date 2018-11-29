package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 26.11.2018
 * MyFreemarkerServlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet(name = "MyFreemarkerServlet", value = "/index")
public class MyFreemarkerServlet extends HttpServlet {

    private UsersService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (UsersService) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDto> users = service.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/ftl/index.ftl").forward(req, resp);
    }
}
