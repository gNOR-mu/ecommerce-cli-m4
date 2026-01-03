package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Customer;
import com.ecommerce.demo.repository.CustomerRepository;

import java.util.Optional;

public class CustomerService implements ReadOnlyService<Customer,Long> {
    //TODO averiguar nombre constante
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
}
