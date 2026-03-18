package com.TP.Produits.Simple.order.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private Long customerId; // Reference to the customer
    private Long productId;
}