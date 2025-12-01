package com.example.product_service.controller;


import com.example.product_service.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return List.of(
                new Product(1L, "Laptop", 1200.0),
                new Product(2L, "Smartphone", 800.0),
                new Product(3L, "Headphones", 150.0)
        );
    }
}
