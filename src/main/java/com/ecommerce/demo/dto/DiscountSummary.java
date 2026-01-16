package com.ecommerce.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public record DiscountSummary(
        List<AppliedDiscount> discounts,
        BigDecimal totalDiscount
) {
}
