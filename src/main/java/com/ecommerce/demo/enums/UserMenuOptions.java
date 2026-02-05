package com.ecommerce.demo.enums;

import com.ecommerce.demo.util.MenuOption;

/**
 * Listado de opciones disponible para el menú de usuario
 *
 * @author Gabriel Norambuena
 * @version 1.0
 */
public enum UserMenuOptions implements MenuOption {
    /**
     * Lista los productos con stock
     */
    LIST_PRODUCTS(1, "Listar productos"),

    /**
     *  Busca productos
     */
    SEARCH_PRODUCTS(2, "Buscar productos"),

    /**
     * Agrega un producto al carrito
     */
    ADD_TO_CART(3, "Agregar al carrito"),

    /**
     * Remueve un producto del carrito
     */
    REMOVE_FROM_CART(4, "Quitar del carro"),

    /**
     * Ve el carrito
     */
    VIEW_CART(5, "Ver carrito"),

    /**
     * Ve los descuentos activos
     */
    VIEW_ACTIVE_DISCOUNTS(6, "Ver descuentos activos"),

    /**
     * Confirma la compra
     */
    CHECKOUT(7, "Confirmar compra"),

    /**
     * Sale del menú
     */
    EXIT(0, "Salir");

    private final int code;
    private final String description;

    UserMenuOptions(int code, String description) {
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
