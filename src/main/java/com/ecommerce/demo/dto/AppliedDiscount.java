package com.ecommerce.demo.dto;

import java.math.BigDecimal;

/**
 * Registro con los descuentos aplicados
 * @param ruleName Nombre de la regla
 * @param amount Monto de descuento
 * @param condition Condición para aplicar el descuento
 * @author Gabriel Norambuena
 * @version 1.0
 */
public record AppliedDiscount(
        String ruleName,
        String condition,
        BigDecimal amount) {}