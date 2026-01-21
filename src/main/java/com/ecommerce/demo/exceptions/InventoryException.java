package com.ecommerce.demo.exceptions;

/**
 * Excepción personalizada para problemas asociados con el inventario.
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class InventoryException extends RuntimeException{
    /**
     * Lanza un {@link RuntimeException}
     * @param msg Error a mostrar.
     */
    public InventoryException(String msg){
        super(msg);
    }
}
