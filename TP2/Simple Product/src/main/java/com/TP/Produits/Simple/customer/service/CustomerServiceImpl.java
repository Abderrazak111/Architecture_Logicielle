package com.TP.Produits.Simple.customer.service;

import com.TP.Produits.Simple.customer.Repository.CustomerRepository;
import com.TP.Produits.Simple.customer.mapper.CustomerMapper;
import com.TP.Produits.Simple.customer.dto.CustomerDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository CustomerRepository, CustomerMapper customerMapper) {
        this.customerRepository = CustomerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    // Requirement 2: Implementation of existence check
    @Override
    public boolean verifyCustomerExists(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        var entity = customerMapper.toEntity(customerDTO);
        var savedEntity = customerRepository.save(entity);
        return customerMapper.toDto(savedEntity);
    }

    @Override
    public CustomerDTO findById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}