package com.example.order_service.model;

import com.example.order_service.dto.ProductDto;

import java.util.List;

public class Order {
    private Long id;
    private String customerName;
    private List<ProductDto> products;


    public Order(Long id, String customerName, List<ProductDto> products) {
        this.id = id;
        this.customerName = customerName;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
