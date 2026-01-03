package main.model;

import main.model.base.Identifiable;

public class OrderItem implements Identifiable<Long> {
    private Long id;
    private Long productId;
    private Long orderId;

    private double subTotal;
    private double unitPrice;
    private int quantity;

    public OrderItem(Long id, Long productId, Long orderId, double subTotal, double unitPrice, int quantity) {
        this.id = id;
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

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
