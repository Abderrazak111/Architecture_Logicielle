package com.SOA.order_server.Service;

import com.SOA.order_server.client.ProductClient;
import com.SOA.order_server.dto.ProductDTO;
import com.SOA.order_server.model.Order;
import com.SOA.order_server.Repository.OrderRepository;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    public List < Order > getAllOrders() {
        return orderRepository.findAll();
    }
    public Order createOrder(Long productId, Integer quantity) {

        try {
            ProductDTO product = productClient.getProductById(productId);

            if (product == null) {
                throw new RuntimeException("Produit introuvable");
            }

            if (product.getStock() < quantity) {
                throw new RuntimeException("Stock insuffisant pour le produit : " + product.getName());
            }

            Order order = new Order();
            order.setProductId(productId);
            order.setProductName(product.getName());
            order.setQuantity(quantity);
            order.setTotalPrice(product.getPrice() * quantity);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus("PENDING");

            Order savedOrder = orderRepository.save(order);

            productClient.updateStock(productId, quantity);

            return savedOrder;

        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Produit introuvable");
        } catch (RetryableException e) {
            throw new RuntimeException("Service produit indisponible");
        } catch (FeignException e) {
            throw new RuntimeException("Erreur du service produit : " + e.getMessage());
        }
    }
}
