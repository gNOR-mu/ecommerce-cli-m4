package com.ecommerce.demo.model;

import java.math.BigDecimal;

/**
 * Item perteneciente al carrito del ecommerce.
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class CartItem {
    private final Product product;
    private int quantity;

    /**
     * Constructor de la clase.
     * @param product Producto.
     * @param quantity Cantidad del producto.
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    /**
     * Obtiene el producto
     * @return Producto
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Obtiene la cantidad del producto
     * @return Cantidad del producto
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad de producto
     * @param quantity Cantidad del producto
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtiene el subtotal del producto
     * @return Subtotal del producto
     */
    public BigDecimal getSubTotal(){
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}
