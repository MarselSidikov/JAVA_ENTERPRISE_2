package ru.itis.services;

import ru.itis.models.Product;

import java.util.List;

/**
 * 29.10.2018
 * ProductsService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface ProductsService {
    List<Product> addProductToUserBasket(String cookieValue, Long productId);
}
