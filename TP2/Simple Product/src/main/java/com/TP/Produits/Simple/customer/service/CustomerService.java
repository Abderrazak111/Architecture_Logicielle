package com.TP.Produits.Simple.customer.service;

import com.TP.Produits.Simple.customer.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAll();
    CustomerDTO findById(Long id);
    CustomerDTO save(CustomerDTO customerDTO);

    // Requirement 2: Method to verify existence
    boolean verifyCustomerExists(Long id);
}
