package com.TP.Produits.Simple.order.service;

import com.TP.Produits.Simple.customer.Repository.CustomerRepository;
import com.TP.Produits.Simple.order.Repository.OrderRepository;
import com.TP.Produits.Simple.product.repository.ProductRepository;
import com.TP.Produits.Simple.product.service.ProductService;
import com.TP.Produits.Simple.order.mapper.OrderMapper;
import com.TP.Produits.Simple.order.dto.OrderDTO;
import com.TP.Produits.Simple.customer.service.CustomerService; // Import from Customer Module
import com.TP.Produits.Simple.order.model.Order;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService; // Injected dependency
    private final ProductService productService;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            CustomerService customerService, ProductService productService, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerService = customerService;
        this.productService = productService;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<OrderDTO> getCustomerOrderHistory(Long customerId) {
        if (!customerService.verifyCustomerExists(customerId)) {
            throw new RuntimeException("Cannot fetch history: Customer does not exist.");
        }

        return orderRepository.findByCustomer_Id(customerId).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDto) {
        // 1. Verify customer exists (Requirement 2)
        if (!customerService.verifyCustomerExists(orderDto.getCustomerId())) {
            throw new RuntimeException("Customer not found with ID: " + orderDto.getCustomerId());
        }
        System.out.println("Attempting to find Product with ID: " + orderDto.getProductId());
        // 2. Verify Product exists (Requirement 2 - Product Module)
        if (!productService.verifyProductExists(orderDto.getProductId())) {
            throw new RuntimeException("Product not found");
        }

        // 2. Map DTO to Entity
        Order order = orderMapper.toEntity(orderDto);
        order.setCustomer(customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found")));
        order.setProduct(productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found")));

        // 3. Save to database
        Order savedOrder = orderRepository.save(order);

        // 4. Return the saved DTO
        return orderMapper.toDto(savedOrder);
    }
}
