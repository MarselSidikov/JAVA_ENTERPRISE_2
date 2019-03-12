package ru.itis.controllers.deprecateds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 09.10.2017
 * UsersControllerDeprecated
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersControllerDeprecated implements Controller {

    @Autowired
    private UsersService usersService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        if (httpServletRequest.getMethod().equals("GET")) {
            List<User> users = usersService.getAllUsers();
            ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject("users", users);
            modelAndView.setViewName("users_page");
            return modelAndView;
        }
        return null;
    }
}
