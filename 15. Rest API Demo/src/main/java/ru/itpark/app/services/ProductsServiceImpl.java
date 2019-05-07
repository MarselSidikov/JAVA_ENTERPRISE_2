package ru.itpark.app.services;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.app.dto.ProductDto;
import ru.itpark.app.models.Product;
import ru.itpark.app.repositories.ProductsRepository;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Product> getAll() {
        return productsRepository.findAll();
    }

    @Override
    public Product add(ProductDto form) {
        Product product = Product.builder()
                .name(form.getName())
                .price(form.getPrice())
                .build();

        return productsRepository.save(product);
    }

    @Override
    public Product update(Long id, ProductDto form) {
        Product product = productsRepository.getOne(id);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        return productsRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public Product get(Long id) {
        Product result = productsRepository.getOne(id);

        if (result instanceof HibernateProxy) {
            return (Product) ((HibernateProxy)result).getHibernateLazyInitializer().getImplementation();
        }
        return result;
    }
}
