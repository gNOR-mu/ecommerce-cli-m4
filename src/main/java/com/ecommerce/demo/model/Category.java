package com.ecommerce.demo.model;

import com.ecommerce.demo.model.base.Identifiable;

/**
 * Modelo categoría de la base de datos
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class Category implements Identifiable<Long> {
    private Long id;

    private String name;

    /**
     * Constructor de la clase
     * @param name Nombre de la categoría
     */
    public Category(String name) {
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
     * Obtiene el nombre de la categoría
     * @return Nombre de la categoría.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la categoría.
     * @param name Nombre de la categoría.
     */
    public void setName(String name) {
        this.name = name;
    }

}
