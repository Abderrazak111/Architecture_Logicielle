package com.TP.Produits.Simple.order.model;

import com.TP.Produits.Simple.customer.model.Customer;
import com.TP.Produits.Simple.product.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; // Links to the Customer Entity

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
