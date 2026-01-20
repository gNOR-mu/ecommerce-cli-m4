package com.ecommerce.demo.model.base;

/**
 * Interfaz para la identificación de entidades.
 * @param <ID> Tipo de la identificación
 * @author Gabriel Norambuena
 * @version 1.0
 */
public interface Identifiable<ID> {
    /**
     * Obtiene la ide de la entidad.
     * @return Identificación de la entidad.
     */
    ID getId();

    /**
     * Establece la identificación.
     * @param id Nueva identificación.
     */
    void setId(ID id);
}
