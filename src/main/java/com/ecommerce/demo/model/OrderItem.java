package com.ecommerce.demo.model;

import com.ecommerce.demo.model.base.Identifiable;

import java.math.BigDecimal;

public class OrderItem implements Identifiable<Long> {
    private Long id;
    private Long productId;
    private Long orderId;

    private BigDecimal subTotal;
    private BigDecimal unitPrice;
    private int quantity;

    public OrderItem(Long productId, Long orderId, BigDecimal subTotal, BigDecimal unitPrice, int quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.subTotal = subTotal;
        this.unitPrice = unitPrice;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
