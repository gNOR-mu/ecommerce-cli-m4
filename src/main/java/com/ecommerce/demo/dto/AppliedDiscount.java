package com.ecommerce.demo.dto;

import java.math.BigDecimal;

/**
 * Registro con los descuentos aplicados
 * @param ruleName Nombre de la regla
 * @param amount Monto de descuento
 */
public record AppliedDiscount(
        String ruleName,
        BigDecimal amount) {}