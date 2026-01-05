package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Order;
import com.ecommerce.demo.repository.OrderRepository;

import java.util.Optional;

public class OrderService implements ReadOnlyService<Order,Long> {
    //TODO averiguar nombre constante
    private final OrderRepository ORDER_REPOSITORY;
    private final CustomerService CUSTOMER_SERVICE;
    private final PaymentService PAYMENT_SERVICE;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, PaymentService paymentService) {
        this.ORDER_REPOSITORY = orderRepository;
        this.CUSTOMER_SERVICE = customerService;
        this.PAYMENT_SERVICE = paymentService;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return ORDER_REPOSITORY.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return ORDER_REPOSITORY.existsById(id);
    }
}
