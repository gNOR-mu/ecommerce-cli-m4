package com.ecommerce.demo.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO para mostrar el resumen de un descuento
 * @param discounts Listado con los descuentos
 * @param totalDiscount Descuentos totales
 */
public record DiscountSummary(
        List<AppliedDiscount> discounts,
        BigDecimal totalDiscount
) {
}
