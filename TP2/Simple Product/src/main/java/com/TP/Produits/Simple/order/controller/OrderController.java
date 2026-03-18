package com.TP.Produits.Simple.order.controller;

import com.TP.Produits.Simple.order.dto.OrderDTO;
import com.TP.Produits.Simple.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getHistory(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getCustomerOrderHistory(customerId));
    }
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDto) {
        // You'll need to implement this method in your OrderService
        return ResponseEntity.ok(orderService.saveOrder(orderDto));
    }
}