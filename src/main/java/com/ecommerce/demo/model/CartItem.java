package com.ecommerce.demo.model;

public class CartItem {
    private final Product PRODUCT;
    private int quantity;

    public CartItem(Product product, int quantity) {
        PRODUCT = product;
    }

    public Product getProduct() {
        return PRODUCT;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
