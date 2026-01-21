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
    private final OrderService orderService;
    private final ProductService productService;

    /**
     * Constructor de la clase
     * @param orderItemRepository Repositorio de orders item
     * @param orderService Servicio de las órdenes
     * @param productService Servicio de los productos
     */
    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
        this.productService = productService;
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
}
