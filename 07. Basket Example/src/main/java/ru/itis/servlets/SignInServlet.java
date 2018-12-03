package ru.itis.servlets;

import ru.itis.forms.SignInForm;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 25.10.2018
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInForm form = SignInForm.builder()
                .name(req.getParameter("name"))
                .password(req.getParameter("password"))
                .build();

        Optional<String> cookieValueCandidate = usersService.signIn(form);
        if (cookieValueCandidate.isPresent()) {
            Cookie auth = new Cookie("auth", cookieValueCandidate.get());
            resp.addCookie(auth);
            resp.sendRedirect("/shop");
        } else {
            resp.sendRedirect("/signIn");
        }
    }
}
