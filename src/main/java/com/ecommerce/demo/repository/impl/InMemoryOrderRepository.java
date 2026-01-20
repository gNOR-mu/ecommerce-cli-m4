package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Order;
import com.ecommerce.demo.repository.OrderRepository;

/**
 * Implementación en memoria de {@link OrderRepository}
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class InMemoryOrderRepository extends InMemoryAbstractRepository<Order> implements OrderRepository {
}
