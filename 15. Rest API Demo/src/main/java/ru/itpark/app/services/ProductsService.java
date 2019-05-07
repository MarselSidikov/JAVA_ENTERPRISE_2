package ru.itpark.app.services;

import ru.itpark.app.dto.ProductDto;
import ru.itpark.app.models.Product;

import java.util.List;

public interface ProductsService {
    List<Product> getAll();

    Product add(ProductDto product);

    Product update(Long id, ProductDto form);

    void delete(Long id);

    Product get(Long id);
}
