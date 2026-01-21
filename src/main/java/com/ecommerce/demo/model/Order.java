package com.ecommerce.demo.model;

import com.ecommerce.demo.model.base.Identifiable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Modelo orden de la base de datos
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class Order implements Identifiable<Long> {
    private Long id;

    private final LocalDateTime creationDate;
    private BigDecimal total;

    /**
     * Constructor de la clase.
     * Establece automáticamente la fecha de creación al momento de crearlo.
     */
    public Order() {
        this.creationDate = LocalDateTime.now();
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
