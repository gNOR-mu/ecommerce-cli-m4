package com.ecommerce.demo.config;

import com.ecommerce.demo.DataSeeder;
import com.ecommerce.demo.repository.*;
import com.ecommerce.demo.repository.impl.*;
import com.ecommerce.demo.service.*;
import com.ecommerce.demo.util.InputHandler;

import java.util.Scanner;

/**
 * Contenedor con las dependencias manejadas en memoria
 *
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

    private final CartService cartService;
    private final CheckoutService checkoutService;

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
        this.orderItemService = new OrderItemService(orderItemRepository);
        this.discountCalculatorService = new DiscountCalculatorService();

        this.cartService = new CartService(productService, inventoryService);
        this.checkoutService = new CheckoutService(orderService, orderItemService, discountCalculatorService, cartService, inventoryService);

        this.scanner = new Scanner(System.in);
        this.inputHandler = new InputHandler(scanner);

        //inicialización de datos
        DataSeeder seeder = new DataSeeder(productService, categoryService);
        seeder.loadData();
    }

    /**
     * Obtiene el servicio de categorías
     * @return Servicio de categorías
     */
    public CategoryService getCategoryService() {
        return categoryService;
    }

    /**
     * Obtiene el servicio de productos
     * @return Servicio de productos
     */
    public ProductService getProductService() {
        return productService;
    }

    /**
     * Obtiene el input handler
     * @return input handler
     */
    public InputHandler getInputHandler() {
        return inputHandler;
    }

    /**
     * Obtiene el servicio de inventario
     * @return Servicio de inventario
     */
    public InventoryService getInventoryService() {
        return inventoryService;
    }

    /**
     * Obtiene el servicio calculador de descuentos
     * @return Servicio calculador de descuentos
     */
    public DiscountCalculatorService getDiscountCalculatorService() {
        return discountCalculatorService;
    }

    /**
     * Obtiene el servicio del carrito
     * @return Servicio del carrito
     */
    public CartService getCartService() {
        return cartService;
    }

    /**
     * Obtiene el servicio del pago
     * @return Servicio del pago
     */
    public CheckoutService getCheckoutService() {
        return checkoutService;
    }
}
