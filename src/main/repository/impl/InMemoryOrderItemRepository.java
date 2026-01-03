package main.repository.impl;

import main.model.OrderItem;
import main.repository.OrderItemRepository;

public class InMemoryOrderItemRepository extends InMemoryAbstractRepository<OrderItem, Long> implements OrderItemRepository {
}
