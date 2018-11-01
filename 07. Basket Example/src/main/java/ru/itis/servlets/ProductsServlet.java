package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 29.10.2018
 * ProductsServlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private List<Integer> ids = new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ids.add(Integer.parseInt(request.getParameter("productId")));
        String json = objectMapper.writeValueAsString(ids);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
