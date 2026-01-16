package com.ecommerce.demo.view.user;

import com.ecommerce.demo.dto.CartProductsDto;
import com.ecommerce.demo.dto.CartSummary;
import com.ecommerce.demo.dto.DiscountSummary;
import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.service.CartService;
import com.ecommerce.demo.service.DiscountCalculatorService;
import com.ecommerce.demo.service.InventoryService;
import com.ecommerce.demo.service.ProductService;
import com.ecommerce.demo.util.PrintUtil;
import com.ecommerce.demo.util.InputHandler;
import com.ecommerce.demo.view.AbstractMenu;

import java.util.List;

public class UserMenu extends AbstractMenu {
    private final ProductService PRODUCT_SERVICE;
    private final CartService CART_SERVICE;
    private final DiscountCalculatorService DISCOUNT_CALCULATOR_SERVICE;
    private final Cart CART;

    public UserMenu(InputHandler inputHandler,
                    ProductService productService,
                    InventoryService inventoryService,
                    DiscountCalculatorService discountCalculatorService) {
        super(inputHandler);
        CART = new Cart();
        PRODUCT_SERVICE = productService;
        DISCOUNT_CALCULATOR_SERVICE = discountCalculatorService;
        CART_SERVICE = new CartService(productService, inventoryService, CART);
    }

    @Override
    protected void printMenuOptions() {
        PrintUtil.printUserMenu();
    }

    @Override
    protected boolean handleOption(int option) {
        switch (option) {
            case 0 -> {
                return false;
            }
            case 1 -> listProducts();
            case 2 -> searchProduct();
            case 3 -> addToCart();
            case 4 -> removeFromCart();
            case 5 -> printCart();
            case 6 -> printDiscounts();
            default -> System.out.println("Opción inválida");
        }
        inputHandler.awaitInput();
        return true;
    }

    private void listProducts() {
        List<ProductSummaryDto> products = PRODUCT_SERVICE.findAllSummary();
        PrintUtil.printProductSummary(products);
    }

    private void searchProduct() {
        String searchText = inputHandler.readText("Texto a buscar nombre/categoría: ");
        List<ProductSummaryDto> res = PRODUCT_SERVICE.search(searchText);
        PrintUtil.printProductSummary(res);
    }

    private void addToCart() {
        long id = inputHandler.readLong("Ingresa la id del producto a agregar al carro: ");
        int quantity = inputHandler.readInt("Cantidad a agregar: ");

        try {
            CART_SERVICE.addToCart(id, quantity);
            System.out.println("Producto añadido al carro");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void removeFromCart() {
        long id = inputHandler.readLong("Ingresa la id del producto a remover del carro: ");
        try {
            CART_SERVICE.removeFromCart(id);
            System.out.println("Producto removido del carro.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printCart() {
        CartSummary cartSummary = CART_SERVICE.getAll();
        PrintUtil.printCartItems(cartSummary);
    }

    private void printDiscounts() {
        DiscountSummary discounts = DISCOUNT_CALCULATOR_SERVICE.applyDiscount(CART);
        PrintUtil.printDiscounts(discounts);
    }
}
