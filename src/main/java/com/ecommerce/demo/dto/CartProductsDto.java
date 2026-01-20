package com.ecommerce.demo.dto;

import java.math.BigDecimal;

/**
 * DTO para mostrar un producto en el carro
 * @param Id Identificación del producto
 * @param name Nombre del producto
 * @param unitPrice Precio unitario
 * @param quantity Cantidad
 * @param subTotal Subtotal
 */
public record CartProductsDto(
        Long Id,
        String name,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal subTotal
){}
