package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Order;
import com.ecommerce.demo.repository.OrderRepository;

public class InMemoryOrderRepository extends InMemoryAbstractRepository<Order, Long> implements OrderRepository {
}
