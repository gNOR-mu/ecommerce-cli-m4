package com.ecommerce.demo.enums;

import com.ecommerce.demo.util.MenuOption;

/**
 * Listado de opciones disponible para el menú de administrador
 * @author Gabriel Norambuena
 * @version 1.0
 */
public enum MainMenuOptions implements MenuOption {
    /**
     * Menú del administrador
     */
    ADMIN(1, "Administrador"),

    /**
     * Menú del usuario
     */
    USER(2, "Usuario"),

    /**
     * Sale de la selección de menúF
     */
    EXIT(0, "Salir");

    private final int code;
    private final String description;

    MainMenuOptions(int code, String description) {
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
