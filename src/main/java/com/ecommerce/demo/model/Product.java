package com.ecommerce.demo.model;


import com.ecommerce.demo.model.base.Identifiable;

import java.math.BigDecimal;

/**
 * Modelo producto item de la base de datos.
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class Product implements Identifiable<Long> {
    private Long id;
    private Long categoryId;

    private BigDecimal price;
    private String name;

    /**
     * Constructor de la clase
     * @param categoryId Identificación de la categoría
     * @param price Precio
     * @param name Nombre
     */
    public Product(Long categoryId, BigDecimal price, String name) {
        this.categoryId = categoryId;
        this.price = price;
        this.name = name;
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
     * Obtiene la identificación de la categoría a la que pertenece
     * @return Identificación de la categoría
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Establece la identificación de la categoría
     * @param categoryId Nueva identificación de la categoría
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Obtiene el precio
     * @return Precio
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Establece el precio
     * @param price Nuevo precio
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Obtiene el nombre
     * @return nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre
     * @param name Nuevo nombre
     */
    public void setName(String name) {
        this.name = name;
    }

}
