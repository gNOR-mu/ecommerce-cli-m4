package com.ecommerce.demo.view.user;

import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.CartItem;
import com.ecommerce.demo.service.CartService;
import com.ecommerce.demo.service.InventoryService;
import com.ecommerce.demo.service.ProductService;
import com.ecommerce.demo.util.FormatUtil;
import com.ecommerce.demo.util.InputHandler;
import com.ecommerce.demo.view.AbstractMenu;

import java.util.List;

public class UserMenu extends AbstractMenu {
    private final ProductService PRODUCT_SERVICE;
    private final CartService CART_SERVICE;

    public UserMenu(InputHandler inputHandler, ProductService productService, InventoryService inventoryService) {
        super(inputHandler);
        PRODUCT_SERVICE = productService;
        CART_SERVICE = new CartService(productService, inventoryService, new Cart());
    }

    @Override
    protected void printMenuOptions() {
        FormatUtil.printUserMenu();
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
            case 4-> removeFromCart();
            case 5 ->printCart();
            default -> System.out.println("Opción inválida");
        }
        inputHandler.awaitInput();
        return true;
    }

    private void listProducts() {
        List<ProductSummaryDto> products = PRODUCT_SERVICE.findAllSummary();
        FormatUtil.printProductSummary(products);
    }

    private void searchProduct() {
        String searchText = inputHandler.readText("Texto a buscar nombre/categoría: ");
        List<ProductSummaryDto> res = PRODUCT_SERVICE.search(searchText);
        FormatUtil.printProductSummary(res);
    }

    private void addToCart() {
        long id = inputHandler.readLong("Ingresa la id del producto a agregar al carro: ");
        int quantity = inputHandler.readInt("Cantidad a agregar: ");

        try {
            CART_SERVICE.addToCart(id, quantity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private void removeFromCart(){
        long id = inputHandler.readLong("Ingresa la id del producto a remover del carro: ");
        try {
            CART_SERVICE.removeFromCart(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printCart(){
        List<CartItem> cartItems = CART_SERVICE.getAll();
    }
}
