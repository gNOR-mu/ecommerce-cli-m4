package com.ecommerce.demo.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Resumen de un carrito
 * @param products Listado con los productos en el carrito
 * @param total Total del carrito
 * @author Gabriel Norambuena
 * @version 1.0
 */
public record CartSummary (
    List<CartProductsDto> products,
    BigDecimal total
){}
