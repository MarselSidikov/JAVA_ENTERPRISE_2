package ru.itpark.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itpark.app.dto.ProductDto;
import ru.itpark.app.models.Product;
import ru.itpark.app.models.User;
import ru.itpark.app.security.authentication.TokenAuthentication;
import ru.itpark.app.services.ProductsService;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    private Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Product> addProduct(@RequestHeader("AUTH") String auth, @RequestBody ProductDto form) {
        return ResponseEntity.status(201).body(service.add(form));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Product>> getProducts(
            @RequestHeader("AUTH") String auth) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info(user.getFirstName());
        List<Product> products = service.getAll();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{product-id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Product> addProduct(@RequestHeader("AUTH") String auth, @PathVariable("product-id") Long id, @RequestBody ProductDto form) {
        return ResponseEntity.status(202).body(service.update(id, form));
    }

    @DeleteMapping("/{product-id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> deleteProduct(@RequestHeader("AUTH") String auth, @PathVariable("product-id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{product-id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Product> getProduct(@RequestHeader("AUTH") String auth, @PathVariable("product-id") Long id) {
        return ResponseEntity.ok(service.get(id));
    }
}
