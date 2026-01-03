package main.service;

import main.model.Order;
import main.repository.OrderRepository;

import java.util.Optional;

public class OrderService implements ReadOnlyService<Order,Long> {
    //TODO averiguar nombre constante
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
}
