package com.ecommerce.demo.view.user;

import com.ecommerce.demo.discount.DiscountRule;
import com.ecommerce.demo.dto.CartSummary;
import com.ecommerce.demo.dto.CheckoutSummaryDto;
import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.service.*;
import com.ecommerce.demo.util.PrintUtil;
import com.ecommerce.demo.util.InputHandler;
import com.ecommerce.demo.view.AbstractMenu;

import java.util.List;

/**
 * Menú de usuario
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class UserMenu extends AbstractMenu {
    private final ProductService productService;
    private final CartService cartService;
    private final DiscountCalculatorService discountCalculatorService;
    private final Cart cart;
    private final CheckoutService checkoutService;

    /**
     * Constructor de la clase.
     * @param inputHandler Clase de utilidad para manejar las entradas.
     * @param productService Servicio de productos
     * @param discountCalculatorService Servicio de descuentos
     * @param inventoryService Servicio de inventario
     * @param cartService Servicio del carrito
     * @param checkoutService Servicio de checkout
     */
    public UserMenu(InputHandler inputHandler,
                    ProductService productService,
                    InventoryService inventoryService,
                    DiscountCalculatorService discountCalculatorService,
                    CartService cartService, CheckoutService checkoutService) {
        super(inputHandler);
        this.productService = productService;
        this.discountCalculatorService = discountCalculatorService;
        this.cartService = cartService;
        this.checkoutService = checkoutService;
        this.cart = new Cart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void printMenuOptions() {
        PrintUtil.printUserMenu();
    }

    /**
     * {@inheritDoc}
     */
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
            case 7 -> confirmPurchase();
            default -> System.out.println("Opción inválida");
        }
        inputHandler.awaitInput();
        return true;
    }

    /**
     * Muestra todos los productos con stock
     */
    private void listProducts() {
        List<ProductSummaryDto> products = productService.findAllWithStock();
        PrintUtil.printProductSummary(products);
    }

    /**
     * Busca productos por nombre o categoría
     */
    private void searchProduct() {
        String searchText = inputHandler.readText("Texto a buscar nombre/categoría: ");
        List<ProductSummaryDto> res = productService.search(searchText);
        PrintUtil.printProductSummary(res);
    }

    /**
     * Añade un producto al carro
     */
    private void addToCart() {
        long id = inputHandler.readLong("Ingresa la id del producto a agregar al carro: ");
        int quantity = inputHandler.readInt("Cantidad a agregar: ");

        try {
            cartService.addToCart(id, quantity, cart);
            System.out.println("Producto añadido al carro");
        } catch (Exception e) {
            System.out.println("No se ha añadido el producto al carro: " + e.getMessage());
        }

    }

    /**
     * Remueve un producto del carro
     */
    private void removeFromCart() {
        long id = inputHandler.readLong("Ingresa la id del producto a remover del carro: ");
        try {
            cartService.removeFromCart(id, cart);
            System.out.println("Producto removido del carro.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Muestra el carrito
     */
    private void printCart() {
        CartSummary cartSummary = cartService.getAll(cart);
        PrintUtil.printCartItems(cartSummary);
    }

    /**
     * Imprime los descuentos activos
     */
    private void printDiscounts() {
        List<DiscountRule> activeDiscounts = discountCalculatorService.getActiveDiscounts();
        PrintUtil.printDiscounts(activeDiscounts);
    }

    /**
     * Confirma la compra
     */
    private void confirmPurchase(){
        try{
            CheckoutSummaryDto checkoutSummary = checkoutService.getSummary(cart);
            PrintUtil.printCheckoutSummary(checkoutSummary);
            String confirm = inputHandler.readText("¿Seguro que quieres continuar con la compra? (Sí/No): ");
            if(!confirm(confirm)){
                System.out.println("Pago cancelado");
                return;
            }

            checkoutService.checkout(cart);
            System.out.println("Compra realizada exitosamente");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
