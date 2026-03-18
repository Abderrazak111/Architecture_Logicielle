package com.TP.Produits.Simple.customer.Repository;

import com.TP.Produits.Simple.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Basic CRUD and existsById are inherited automatically
}