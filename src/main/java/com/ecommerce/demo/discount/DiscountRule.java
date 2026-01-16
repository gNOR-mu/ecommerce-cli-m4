package com.ecommerce.demo.discount;

import com.ecommerce.demo.model.Cart;

import java.math.BigDecimal;

/**
 * Interfaz para definir las reglas de descuento que aplican a los productos.
 * @author Gabriel Norambuena
 */
public interface DiscountRule {
    /**
     * Verifica si un carrito determinado es aplicable o no para los descuentos
     * @param cart carrito a evaluar
     * @return verdadero si aplican los descuentos, falso en caso contrario
     */
    boolean isApplicable(Cart cart);

    /**
     * Descuento correspondiente a un carrito.
     * @param cart carrito a calcular el descuento
     * @return Descuento
     */
    BigDecimal calculateDiscount();

    /**
     * Obtiene el nombre de la regla de descuento
     * @return Nombre del descuento
     */
    String getName();
}
