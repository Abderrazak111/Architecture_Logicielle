package com.TP.Produits.Simple.order.service;

import com.TP.Produits.Simple.order.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getCustomerOrderHistory(Long customerId);
    OrderDTO saveOrder(OrderDTO orderDto);
}