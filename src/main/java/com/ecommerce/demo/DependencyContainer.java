package com.ecommerce.demo;

import com.ecommerce.demo.repository.*;
import com.ecommerce.demo.repository.impl.*;
import com.ecommerce.demo.service.*;
import com.ecommerce.demo.util.InputHandler;

import java.util.Scanner;

/**
 * Contenedor con las dependencias manejadas en memoria
 */
public class DependencyContainer {
    private final CategoryRepository CATEGORY_REPOSITORY;
    private final InventoryRepository INVENTORY_REPOSITORY;
    private final OrderItemRepository ORDER_ITEM_REPOSITORY;
    private final OrderRepository ORDER_REPOSITORY;
    private final ProductRepository PRODUCT_REPOSITORY;

    private final CategoryService CATEGORY_SERVICE;
    private final ProductService PRODUCT_SERVICE;
    private final OrderService ORDER_SERVICE;
    private final OrderItemService ORDER_ITEM_SERVICE;
    private final InventoryService INVENTORY_SERVICE;
    private final DiscountCalculatorService DISCOUNT_CALCULATOR_SERVICE;

    private final Scanner SCANNER;
    private final InputHandler INPUT_HANDLER;

    /**
     * Constructor de la clase
     */
    public DependencyContainer() {
        // creación de repositorios
        this.CATEGORY_REPOSITORY = new InMemoryCategoryRepository();
        this.INVENTORY_REPOSITORY = new InMemoryInventoryRepository();
        this.ORDER_ITEM_REPOSITORY = new InMemoryOrderItemRepository();
        this.ORDER_REPOSITORY = new InMemoryOrderRepository();
        this.PRODUCT_REPOSITORY = new InMemoryProductRepository();

        //creación de servicios
        this.CATEGORY_SERVICE = new CategoryService(CATEGORY_REPOSITORY);
        this.INVENTORY_SERVICE = new InventoryService(INVENTORY_REPOSITORY);
        this.PRODUCT_SERVICE = new ProductService(PRODUCT_REPOSITORY, CATEGORY_SERVICE, INVENTORY_SERVICE);
        this.ORDER_SERVICE = new OrderService(ORDER_REPOSITORY);
        this.ORDER_ITEM_SERVICE = new OrderItemService(ORDER_ITEM_REPOSITORY, ORDER_SERVICE, PRODUCT_SERVICE);
        this.DISCOUNT_CALCULATOR_SERVICE = new DiscountCalculatorService();

        this.SCANNER = new Scanner(System.in);
        this.INPUT_HANDLER = new InputHandler(SCANNER);

        //inicialización de datos
        DataSeeder seeder = new DataSeeder(PRODUCT_SERVICE, CATEGORY_SERVICE);
        seeder.loadData();
    }

    public CategoryRepository getCategoryRepository() {
        return CATEGORY_REPOSITORY;
    }


    public InventoryRepository getInventoryRepository() {
        return INVENTORY_REPOSITORY;
    }

    public OrderItemRepository getOrderItemRepository() {
        return ORDER_ITEM_REPOSITORY;
    }

    public OrderRepository getOrderRepository() {
        return ORDER_REPOSITORY;
    }


    public ProductRepository getProductRepository() {
        return PRODUCT_REPOSITORY;
    }

    public CategoryService getCategoryService() {
        return CATEGORY_SERVICE;
    }

    public ProductService getProductService() {
        return PRODUCT_SERVICE;
    }

    public OrderService getOrderService() {
        return ORDER_SERVICE;
    }

    public OrderItemService getOrderItemService() {
        return ORDER_ITEM_SERVICE;
    }

    public Scanner getScanner() {
        return SCANNER;
    }

    public InputHandler getInputHandler() {
        return INPUT_HANDLER;
    }

    public InventoryService getInventoryService() {
        return INVENTORY_SERVICE;
    }

    public DiscountCalculatorService getDiscountCalculatorService() {
        return DISCOUNT_CALCULATOR_SERVICE;
    }
}
