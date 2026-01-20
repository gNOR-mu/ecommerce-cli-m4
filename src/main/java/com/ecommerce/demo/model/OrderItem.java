package com.ecommerce.demo.model;

import com.ecommerce.demo.model.base.Identifiable;

import java.math.BigDecimal;

/**
 * Modelo orden item de la base de datos.
 * Contiene información respecto a los productos de una orden determinada.
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class OrderItem implements Identifiable<Long> {
    private Long id;
    private Long productId;
    private Long orderId;

    private BigDecimal subTotal;
    private BigDecimal unitPrice;
    private int quantity;

    /**
     * Constructor de la clase
     * @param productId Identificación del producto
     * @param orderId Identificación de la orden
     * @param subTotal Subtotal
     * @param unitPrice Precio unitario
     * @param quantity Cantidad
     */
    public OrderItem(Long productId, Long orderId, BigDecimal subTotal, BigDecimal unitPrice, int quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.subTotal = subTotal;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
