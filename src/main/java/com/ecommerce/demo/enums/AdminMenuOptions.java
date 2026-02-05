package com.ecommerce.demo.enums;

import com.ecommerce.demo.util.MenuOption;

/**
 * Listado de opciones disponible para el menú de administrador
 * @author Gabriel Norambuena
 * @version 1.0
 */
public enum AdminMenuOptions implements MenuOption {
    /**
     * Lista los productos
     */
    LIST_PRODUCTS(1, "Listar productos"),

    /**
     * Busca un producto por nombre o categoría
     */
    SEARCH_PRODUCT(2, "Buscar (nombre/categoría)"),

    /**
     * Crea un nuevo producto
     */
    CREATE_PRODUCT(3, "Crear producto"),

    /**
     * Edita un producto
     */
    UPDATE_PRODUCT(4, "Editar producto"),

    /**
     * Elimina un producto
     */
    DELETE_PRODUCT(5, "Eliminar producto"),

    /**
     * Sale de menú
     */
    EXIT(0, "Salir");

    private final int code;
    private final String description;

    AdminMenuOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
