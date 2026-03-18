package com.TP.Produits.Simple.order.mapper;

import com.TP.Produits.Simple.order.dto.OrderDTO;
import com.TP.Produits.Simple.order.model.Order;
import org.springframework.stereotype.Component;

@Component

public class OrderMapper {
    public OrderDTO toDto(Order order) {
        if (order == null) return null;
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderDate(order.getOrderDate());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setProductId(order.getProduct().getId()); // Map Product ID
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        if (dto == null) return null;
        Order order = new Order();
        order.setTotalAmount(dto.getTotalAmount());
        order.setOrderDate(dto.getOrderDate());
        // Entities are linked in the Service layer using the IDs
        return order;
    }
}