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
    private final OrderItemRepository ORDER_ITEM_REPOSITORY;
    private final OrderService ORDER_SERVICE;
    private final ProductService PRODUCT_SERVICE;

    /**
     * Constructor de la clase
     * @param orderItemRepository Repositorio de orders item
     * @param orderService Servicio de las órdenes
     * @param productService Servicio de los productos
     */
    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService, ProductService productService) {
        this.ORDER_ITEM_REPOSITORY = orderItemRepository;
        this.ORDER_SERVICE = orderService;
        this.PRODUCT_SERVICE = productService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderItem getById(Long id) throws ResourceNotFoundException {
        return ORDER_ITEM_REPOSITORY.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden Item",id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean notExistsById(Long id) {
        return !ORDER_ITEM_REPOSITORY.existsById(id);
    }
}
