package ru.itis.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.services.UsersService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 29.10.2018
 * ComponentsListener
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class ComponentsListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("ru.itis/context.xml");
        UsersService usersService = context.getBean(UsersService.class);

        sce.getServletContext().setAttribute("usersService", usersService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
