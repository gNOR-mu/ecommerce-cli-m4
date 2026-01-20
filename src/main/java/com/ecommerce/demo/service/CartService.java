package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.CartProductsDto;
import com.ecommerce.demo.dto.CartSummary;
import com.ecommerce.demo.exceptions.InventoryException;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.CartItem;
import com.ecommerce.demo.model.Product;

import java.util.List;

/**
 * Servicio para manipular el carrito de compras
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class CartService {

    private final ProductService PRODUCT_SERVICE;
    private final InventoryService INVENTORY_SERVICE;

    private final Cart CART;

    /**
     * Constructor de la clase.
     * @param productService Servicio de los productos
     * @param inventoryService Servicio del inventario.
     * @param cart Carrito de compras.
     */
    public CartService(ProductService productService, InventoryService inventoryService, Cart cart) {
        this.PRODUCT_SERVICE = productService;
        this.INVENTORY_SERVICE = inventoryService;
        this.CART = cart;
    }

    /**
     * Añade un nuevo producto al carro.
     * @param productId Identificación del producto.
     * @param quantity Cantidad a añadir.
     * @throws IllegalArgumentException Cuando se ingresa una cantidad <= 0.
     * @throws InventoryException Cuando se intenta añadir al carro un producto sin stock.
     * @throws InventoryException Cuando se intenta añadir más productos que los disponibles en el stock.
     */
    public void addToCart(Long productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("No se puede añadir una cantidad negativa.");
        }

        Product product = PRODUCT_SERVICE.getById(productId);
        int inventory = INVENTORY_SERVICE.getByProductId(productId).getQuantity();
        int cartQuantity = CART.getCartItem(productId).map(CartItem::getQuantity).orElse(0);

        if (inventory == 0) {
            throw new InventoryException("No hay stock disponible");
        }

        if (inventory < (cartQuantity + quantity)) {
            throw new InventoryException("No puedes añadir más productos de los que hay disponibles");
        }
        CART.addProduct(product, quantity);
    }

    /**
     * Elimina un producto del carro, independientemente de la cantidad del mismo.
     * @param productId Identificación del producto a eliminar
     *
     * @throws ResourceNotFoundException Cuando el producto no se encuentra en el carro.
     */
    public void removeFromCart(Long productId) {
        CART.removeItem(productId).orElseThrow(() ->
                new ResourceNotFoundException("No existe un producto con la id : %d en el carro.".formatted(productId))
        );
    }

    /**
     * Obtiene todos los elementos del carro.
     * @return Un resumen con todos los elementos del carro.
     */
    public CartSummary getAll() {
        List<CartProductsDto> productsDto = CART.getAllItems().stream()
                .map(item -> new CartProductsDto(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity(),
                        item.getSubTotal()
                )).toList();

        return new CartSummary(productsDto, CART.getTotal());
    }

    /**
     * Vacía el carro.
     */
    public void clear() {
        CART.clear();
    }
}
