package com.ecommerce.demo.model;

import com.ecommerce.demo.model.base.Identifiable;

/**
 * Modelo inventario de la base de datos
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class Inventory implements Identifiable<Long> {
    private Long id;
    private final Long productId;

    private int quantity;

    /**
     * Constructor de la clase.
     * @param productId Identificación del producto relacionado con el inventario
     * @param quantity Cantidad de inventario
     */
    public Inventory(Long productId, int quantity) {
        this.productId = productId;
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

    /**
     * Obtiene la Identificación del producto
     * @return Identificación del producto
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Obtiene la cantidad del producto.
     * @return Cantidad del producto
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad del producto
     * @param quantity Nueva cantidad del producto
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
