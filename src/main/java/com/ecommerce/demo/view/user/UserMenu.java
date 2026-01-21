package com.ecommerce.demo.view.user;

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
    private final ProductService productService;
    private final CartService cartService;
    private final DiscountCalculatorService discountCalculatorService;
    private final Cart cart;

    public UserMenu(InputHandler inputHandler,
                    ProductService productService,
                    InventoryService inventoryService,
                    DiscountCalculatorService discountCalculatorService) {
        super(inputHandler);
        this.productService = productService;
        this.discountCalculatorService = discountCalculatorService;
        this.cart = new Cart();
        this.cartService = new CartService(productService, inventoryService, new Cart());
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
        List<ProductSummaryDto> products = productService.findAllSummary();
        PrintUtil.printProductSummary(products);
    }

    private void searchProduct() {
        String searchText = inputHandler.readText("Texto a buscar nombre/categoría: ");
        List<ProductSummaryDto> res = productService.search(searchText);
        PrintUtil.printProductSummary(res);
    }

    private void addToCart() {
        long id = inputHandler.readLong("Ingresa la id del producto a agregar al carro: ");
        int quantity = inputHandler.readInt("Cantidad a agregar: ");

        try {
            cartService.addToCart(id, quantity);
            System.out.println("Producto añadido al carro");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void removeFromCart() {
        long id = inputHandler.readLong("Ingresa la id del producto a remover del carro: ");
        try {
            cartService.removeFromCart(id);
            System.out.println("Producto removido del carro.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printCart() {
        CartSummary cartSummary = cartService.getAll();
        PrintUtil.printCartItems(cartSummary);
    }

    private void printDiscounts() {
        DiscountSummary discounts = discountCalculatorService.applyDiscount(cart);
        PrintUtil.printDiscounts(discounts);
    }
}
