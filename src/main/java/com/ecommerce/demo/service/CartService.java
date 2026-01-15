package com.ecommerce.demo.service;

import com.ecommerce.demo.exceptions.CartException;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.CartItem;
import com.ecommerce.demo.model.Product;

import java.util.List;

public class CartService {

    private final ProductService PRODUCT_SERVICE;
    private final InventoryService INVENTORY_SERVICE;

    private final Cart CART;

    public CartService(ProductService productService, InventoryService inventoryService, Cart cart) {
        this.PRODUCT_SERVICE = productService;
        this.INVENTORY_SERVICE = inventoryService;
        this.CART = cart;
    }

    public void addToCart(Long productId, int quantity) {
        Product product = PRODUCT_SERVICE.getById(productId);
        int inventory = INVENTORY_SERVICE.getByProductId(productId).getQuantity();
        int cartQuantity = CART.getCartItem(productId).map(CartItem::getQuantity).orElse(0);

        if (inventory < (cartQuantity + quantity)) {
            throw new CartException("No puedes añadir más productos de los que hay disponibles");
        }
        CART.addProduct(product, quantity);
    }

    public void removeFromCart(Long productId) throws  CartException{
        CART.removeItem(productId).orElseThrow(() ->
                new CartException("No existe un producto con la id " + productId + " en el carro.")
        );
    }

    public List<CartItem> getAll(){
        return CART.getAll();
    }

    public void clear() {
        CART.clear();
    }
}
