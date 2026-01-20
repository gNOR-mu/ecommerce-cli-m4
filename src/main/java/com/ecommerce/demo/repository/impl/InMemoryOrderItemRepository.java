package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.OrderItem;
import com.ecommerce.demo.repository.OrderItemRepository;

/**
 * Implementación en memoria de {@link OrderItemRepository}
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class InMemoryOrderItemRepository extends InMemoryAbstractRepository<OrderItem> implements OrderItemRepository {
}
