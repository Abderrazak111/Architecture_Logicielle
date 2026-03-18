package com.TP.Produits.Simple.customer.controller;

import com.TP.Produits.Simple.customer.dto.CustomerDTO;
import com.TP.Produits.Simple.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Requirement 1: Standard CRUD - Get all customers
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.findAll();
    }

    // Requirement 2: Logic for verifying existence is usually
    // internal, but can be exposed via a specific endpoint
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkCustomerExists(@PathVariable Long id) {
        boolean exists = customerService.verifyCustomerExists(id);
        return ResponseEntity.ok(exists);
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }
}
