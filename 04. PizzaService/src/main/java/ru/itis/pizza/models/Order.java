package ru.itis.pizza.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 03.09.2018
 * Order
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Getter
@Setter
@ToString(exclude = "client")
@EqualsAndHashCode
@Builder
public class Order {
    private Long id;
    private User client;
    private List<Pizza> pizzas;
    private String address;
    private LocalDateTime dateTime;
}
