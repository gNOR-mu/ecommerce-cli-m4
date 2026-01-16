package com.ecommerce.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartSummary (
    List<CartProductsDto> products,
    BigDecimal total
){}
