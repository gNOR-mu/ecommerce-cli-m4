package main.repository.impl;

import main.model.Order;
import main.repository.OrderRepository;

public class InMemoryOrderRepository extends InMemoryAbstractRepository<Order, Long> implements OrderRepository {
}
