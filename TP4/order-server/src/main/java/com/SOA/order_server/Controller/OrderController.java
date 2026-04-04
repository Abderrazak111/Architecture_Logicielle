package com.SOA.order_server.Controller;

import com.SOA.order_server.model.Order;
import com.SOA.order_server.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping public List < Order > getAllOrders() {
        return orderService.getAllOrders();
    }
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestParam Long productId,
                                         @RequestParam Integer quantity) {
        try {
            Order order = orderService.createOrder(productId, quantity);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
