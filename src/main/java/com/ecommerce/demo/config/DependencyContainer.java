package com.ecommerce.demo.config;

import com.ecommerce.demo.DataSeeder;
import com.ecommerce.demo.repository.*;
import com.ecommerce.demo.repository.impl.*;
import com.ecommerce.demo.service.*;
import com.ecommerce.demo.util.InputHandler;

import java.util.Scanner;

/**
 * Contenedor con las dependencias manejadas en memoria
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class DependencyContainer {
    private final CategoryRepository categoryRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final InventoryService inventoryService;
    private final DiscountCalculatorService discountCalculatorService;

    private final Scanner scanner;
    private final InputHandler inputHandler;

    /**
     * Constructor de la clase
     */
    public DependencyContainer() {
        // creación de repositorios
        this.categoryRepository = new InMemoryCategoryRepository();
        this.inventoryRepository = new InMemoryInventoryRepository();
        this.orderItemRepository = new InMemoryOrderItemRepository();
        this.orderRepository = new InMemoryOrderRepository();
        this.productRepository = new InMemoryProductRepository();

        //creación de servicios
        this.categoryService = new CategoryService(categoryRepository);
        this.inventoryService = new InventoryService(inventoryRepository);
        this.productService = new ProductService(productRepository, categoryService, inventoryService);
        this.orderService = new OrderService(orderRepository);
        this.orderItemService = new OrderItemService(orderItemRepository, orderService, productService);
        this.discountCalculatorService = new DiscountCalculatorService();

        this.scanner = new Scanner(System.in);
        this.inputHandler = new InputHandler(scanner);

        //inicialización de datos
        DataSeeder seeder = new DataSeeder(productService, categoryService);
        seeder.loadData();
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }


    public InventoryRepository getInventoryRepository() {
        return inventoryRepository;
    }

    public OrderItemRepository getOrderItemRepository() {
        return orderItemRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }


    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public OrderItemService getOrderItemService() {
        return orderItemService;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }

    public DiscountCalculatorService getDiscountCalculatorService() {
        return discountCalculatorService;
    }
}
