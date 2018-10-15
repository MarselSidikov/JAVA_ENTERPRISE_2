package ru.itis.pizza.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 15.10.2018
 * LocalizationFilter
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebFilter("*")
public class LocalizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String lang = request.getParameter("lang");

        if (lang != null) {
            Cookie cookie = new Cookie("locale", lang);
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
        } else {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("locale")) {
                    lang = cookie.getValue();
                }
            }
            if (lang == null) {
                lang = "En";
            }
        }

        Map<String, String> locale = (Map<String, String>) request.getServletContext().getAttribute("locale" + lang);
        request.setAttribute("locale", locale);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
