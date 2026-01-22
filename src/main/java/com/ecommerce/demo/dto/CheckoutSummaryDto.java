package com.ecommerce.demo.dto;

import java.math.BigDecimal;

public record CheckoutSummaryDto(
        CartSummary cartSummary,
        DiscountSummary discountSummary,
        BigDecimal finalPrice
) {

}
