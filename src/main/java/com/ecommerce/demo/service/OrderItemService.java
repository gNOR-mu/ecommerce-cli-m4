package com.ecommerce.demo.service;

import com.ecommerce.demo.model.OrderItem;
import com.ecommerce.demo.repository.OrderItemRepository;

import java.util.Optional;

public class OrderItemService implements ReadOnlyService<OrderItem, Long> {
    //TODO averiguar nombre constante
    private final OrderItemRepository ORDER_ITEM_REPOSITORY;
    private final OrderService ORDER_SERVICE;
    private final ProductService PRODUCT_SERVICE;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService, ProductService productService) {
        this.ORDER_ITEM_REPOSITORY = orderItemRepository;
        this.ORDER_SERVICE = orderService;
        this.PRODUCT_SERVICE = productService;
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return ORDER_ITEM_REPOSITORY.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return ORDER_ITEM_REPOSITORY.existsById(id);
    }
}
