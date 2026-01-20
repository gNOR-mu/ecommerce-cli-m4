package com.ecommerce.demo.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Resumen de un carrito
 * @param products Listado con los productos en el carrito
 * @param total Total del carrito
 */
public record CartSummary (
    List<CartProductsDto> products,
    BigDecimal total
){}
