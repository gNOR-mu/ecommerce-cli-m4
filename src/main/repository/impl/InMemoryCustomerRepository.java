package main.repository.impl;

import main.model.Customer;
import main.repository.CustomerRepository;

public class InMemoryCustomerRepository extends InMemoryAbstractRepository<Customer, Long> implements CustomerRepository {
}
