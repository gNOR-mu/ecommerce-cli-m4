package com.ecommerce.demo.dto;

import java.math.BigDecimal;

/**
 * Resumen del pago
 * @param cartSummary Resumen del carrito
 * @param discountSummary Resumen del descuento
 * @param finalPrice Precio final
 * @author Gabriel Norambuena
 * @version 1.0
 */
public record CheckoutSummaryDto(
        CartSummary cartSummary,
        DiscountSummary discountSummary,
        BigDecimal finalPrice
) {

}
