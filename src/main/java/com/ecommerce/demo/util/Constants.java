package com.ecommerce.demo.util;

import com.ecommerce.demo.discount.DiscountRule;
import com.ecommerce.demo.discount.rules.CategoryDiscountRule;
import com.ecommerce.demo.discount.rules.QuantityDiscountRule;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase de utilidad que contiene constantes
 * @author Gabriel Norambuena
 * @version 1.0
 */
public final class Constants {
    private Constants(){}

    /* Opciones de confirmación*/
    /**
     * Listado de opciones posibles para la confirmación.
     */
    public static final List<String> CONFIRMATION = List.of("sí", "si");

    /* REGLAS DE DESCUENTO */
    /**
     * Umbral sobre el que se aplica la regla de cantidad de productos.
     * Siempre es {@value }
     */
    public static final int QUANTITY_THRESHOLD = 10;

    /**
     * Descuento que aplica a la regla de cantidad de productos
     */
    public static final BigDecimal QUANTITY_DISCOUNT_AMOUNT = new BigDecimal("5");


    /**
     * Descuento que se aplica al tener {@value} categorías distintas.
     */
    public static final int CATEGORIES_THRESHOLD = 2;

    /**
     * Descuento que aplica a la regla de cantidad de categorías
     */
    public static final BigDecimal CATEGORIES_DISCOUNT_AMOUNT = new BigDecimal("20");

    /**
     * Reglas activas de descuento.
     */
    public static final List<DiscountRule> ACTIVE_RULES = List.of(
            new QuantityDiscountRule(),
            new CategoryDiscountRule()
    );


}
