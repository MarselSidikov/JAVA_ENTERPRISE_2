package ru.itis.pizza.context;

import ru.itis.pizza.localization.Localizations;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

/**
 * 15.10.2018
 * LocaleListener
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class LocaleListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Map<String, String> localeEn = Localizations.loadLocalization("en");
        Map<String, String> localeRu = Localizations.loadLocalization("ru");
        Map<String, String> localePo = Localizations.loadLocalization("po");
        context.setAttribute("localeEn", localeEn);
        context.setAttribute("localeRu", localeRu);
        context.setAttribute("localePo", localePo);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
