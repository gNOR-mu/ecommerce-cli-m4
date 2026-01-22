package com.ecommerce.demo.service;

import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.OrderItem;
import com.ecommerce.demo.repository.OrderItemRepository;

/**
 * Servicio para la gestión de elementos de las órdenes
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class OrderItemService implements IdentifiableService<OrderItem, Long> {
    private final OrderItemRepository orderItemRepository;

    /**
     * Constructor de la clase
     * @param orderItemRepository Repositorio de orders item
     */
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderItem getById(Long id) throws ResourceNotFoundException {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden Item",id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean notExistsById(Long id) {
        return !orderItemRepository.existsById(id);
    }

    /**
     * Crea un nuevo Order Item
     */
    public OrderItem create(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}
