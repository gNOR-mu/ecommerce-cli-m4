package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Customer;
import com.ecommerce.demo.repository.CustomerRepository;

public class InMemoryCustomerRepository extends InMemoryAbstractRepository<Customer, Long> implements CustomerRepository {
}
