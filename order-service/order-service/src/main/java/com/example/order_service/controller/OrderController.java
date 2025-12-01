package com.example.order_service.controller;


import com.example.order_service.client.ProductClient;
import com.example.order_service.dto.ProductDto;
import com.example.order_service.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    private final ProductClient productClient;

    public OrderController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        List<ProductDto> products = productClient.getProducts();

        Order order1 = new Order(1L, "Customer A", products);
        Order order2 = new Order(2L, "Customer B", products.subList(0, 2));

        return List.of(order1, order2);
    }

}
