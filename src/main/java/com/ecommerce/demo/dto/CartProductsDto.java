package com.ecommerce.demo.dto;

import java.math.BigDecimal;

public record CartProductsDto(
        Long ID,
        String name,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal subTotal
){}
