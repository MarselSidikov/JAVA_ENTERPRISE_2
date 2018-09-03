package ru.itis.pizza.models;

import java.util.List;

/**
 * 03.09.2018
 * Order
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Order {
    private Long id;
    private User client;
    private List<Pizza> pizzas;
}
