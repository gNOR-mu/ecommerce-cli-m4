package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Customer;
import com.ecommerce.demo.repository.CustomerRepository;

import java.util.Optional;

public class CustomerService implements ReadOnlyService<Customer,Long> {
    //TODO averiguar nombre constante
    private final CustomerRepository CUSTOMER_REPOSITORY;

    public CustomerService(CustomerRepository customerRepository) {
        this.CUSTOMER_REPOSITORY = customerRepository;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return CUSTOMER_REPOSITORY.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return CUSTOMER_REPOSITORY.existsById(id);
    }
}
