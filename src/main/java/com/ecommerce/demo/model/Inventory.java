package com.ecommerce.demo.model;

import com.ecommerce.demo.model.base.Identifiable;

public class Inventory implements Identifiable<Long> {
    private Long id;
    private Long productId;

    private int quantity;

    public Inventory(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
