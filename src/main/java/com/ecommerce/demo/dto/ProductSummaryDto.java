package com.ecommerce.demo.dto;

import java.math.BigDecimal;

/**
 * DTO para mostrar el resumen de un producto
 * @param id Identificación
 * @param name Nombre del producto
 * @param category Nombre de la categoría
 * @param price Precio del producto
 * @param stock Cantidad del producto
 * @param categoryId Identificación de la categoría
 * @author Gabriel Norambuena
 * @version 1.0
 */
public record ProductSummaryDto(
        Long id,
        String name,
        String category,
        BigDecimal price,
        int stock,
        Long categoryId
){}