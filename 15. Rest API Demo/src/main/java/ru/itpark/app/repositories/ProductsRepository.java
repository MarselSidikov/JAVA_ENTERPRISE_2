package ru.itpark.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.app.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {
}
