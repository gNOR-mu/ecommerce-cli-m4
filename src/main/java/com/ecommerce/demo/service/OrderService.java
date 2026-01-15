package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Order;
import com.ecommerce.demo.repository.OrderRepository;

import java.util.Optional;

public class OrderService implements ReadOnlyService<Order,Long> {
    private final OrderRepository ORDER_REPOSITORY;

    public OrderService(OrderRepository orderRepository) {
        this.ORDER_REPOSITORY = orderRepository;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return ORDER_REPOSITORY.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return ORDER_REPOSITORY.existsById(id);
    }


    /**
     * Crea una nueva orden.
     * @return ID de la orden generada
     */
    public Long createORder(){
        return ORDER_REPOSITORY.save(new Order()).getId();
    }
}
