package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.OrderItem;
import com.ecommerce.demo.repository.OrderItemRepository;

public class InMemoryOrderItemRepository extends InMemoryAbstractRepository<OrderItem, Long> implements OrderItemRepository {
}
