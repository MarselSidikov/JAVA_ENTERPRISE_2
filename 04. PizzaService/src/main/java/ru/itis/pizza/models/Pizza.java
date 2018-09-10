package ru.itis.pizza.models;

import lombok.*;

/**
 * 03.09.2018
 * Pizza
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Pizza {
    private Long id;
    private String name;
    private Double price;
}
