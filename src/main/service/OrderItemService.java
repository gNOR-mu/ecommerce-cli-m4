package main.service;

import main.model.OrderItem;
import main.repository.OrderItemRepository;

import java.util.Optional;

public class OrderItemService implements ReadOnlyService<OrderItem, Long> {
    //TODO averiguar nombre constante
    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;
    private final ProductService productService;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }
}
