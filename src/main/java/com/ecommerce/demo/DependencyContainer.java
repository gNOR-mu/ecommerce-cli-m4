package com.ecommerce.demo;

import com.ecommerce.demo.repository.*;
import com.ecommerce.demo.repository.impl.*;
import com.ecommerce.demo.service.*;

public class DependencyContainer {
    //TODO evaluar nombre de las constantes.
    private final CategoryRepository CATEGORY_REPOSITORY;
    private final CustomerRepository CUSTOMER_REPOSITORY;
    private final InventoryRepository INVENTORY_REPOSITORY;
    private final OrderItemRepository ORDER_ITEM_REPOSITORY;
    private final OrderRepository ORDER_REPOSITORY;
    private final PaymentRepository PAYMENT_REPOSITORY;
    private final ProductRepository PRODUCT_REPOSITORY;

    private final CategoryService CATEGORY_SERVICE;
    private final CustomerService CUSTOMER_SERVICE;
    private final PaymentService PAYMENT_SERVICE;
    private final ProductService PRODUCT_SERVICE;
    private final OrderService ORDER_SERVICE;
    private final OrderItemService ORDER_ITEM_SERVICE;

    public DependencyContainer() {
        // creación de repositorios
        this.CATEGORY_REPOSITORY = new InMemoryCategoryRepository();
        this.CUSTOMER_REPOSITORY = new InMemoryCustomerRepository();
        this.INVENTORY_REPOSITORY = new InMemoryInventoryRepository();
        this.ORDER_ITEM_REPOSITORY = new InMemoryOrderItemRepository();
        this.ORDER_REPOSITORY = new InMemoryOrderRepository();
        this.PAYMENT_REPOSITORY = new InMemoryPaymentRepository();
        this.PRODUCT_REPOSITORY = new InMemoryProductRepository();

        //creación de servicios
        this.CATEGORY_SERVICE = new CategoryService(CATEGORY_REPOSITORY);
        this.CUSTOMER_SERVICE = new CustomerService(CUSTOMER_REPOSITORY);
        this.PAYMENT_SERVICE = new PaymentService(PAYMENT_REPOSITORY);
        this.PRODUCT_SERVICE = new ProductService(PRODUCT_REPOSITORY, CATEGORY_SERVICE);
        this.ORDER_SERVICE = new OrderService(ORDER_REPOSITORY, CUSTOMER_SERVICE, PAYMENT_SERVICE);
        this.ORDER_ITEM_SERVICE = new OrderItemService(ORDER_ITEM_REPOSITORY, ORDER_SERVICE, PRODUCT_SERVICE);

        //inicialización de datos
        DataSeeder seeder = new DataSeeder(PRODUCT_SERVICE, CATEGORY_SERVICE);
        seeder.loadData();
    }

    public CategoryRepository getCategoryRepository() {
        return CATEGORY_REPOSITORY;
    }

    public CustomerRepository getCustomerRepository() {
        return CUSTOMER_REPOSITORY;
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

    public PaymentRepository getPaymentRepository() {
        return PAYMENT_REPOSITORY;
    }

    public ProductRepository getProductRepository() {
        return PRODUCT_REPOSITORY;
    }

    public CategoryService getCategoryService() {
        return CATEGORY_SERVICE;
    }

    public CustomerService getCustomerService() {
        return CUSTOMER_SERVICE;
    }

    public PaymentService getPaymentService() {
        return PAYMENT_SERVICE;
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
}
