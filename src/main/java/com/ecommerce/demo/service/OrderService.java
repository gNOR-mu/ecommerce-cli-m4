package com.ecommerce.demo.service;

import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Order;
import com.ecommerce.demo.repository.OrderRepository;

/**
 * Servicio para la gestión de órdenes
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class OrderService implements IdentifiableService<Order,Long> {
    private final OrderRepository orderRepository;

    /**
     * Constructor de la clase
     * @param orderRepository Repositorio de las órdenes
     */
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Orden", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean notExistsById(Long id) {
        return !orderRepository.existsById(id);
    }

}
