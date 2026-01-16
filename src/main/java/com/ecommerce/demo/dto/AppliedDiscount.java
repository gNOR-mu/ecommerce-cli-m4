package com.ecommerce.demo.dto;

import java.math.BigDecimal;

public record AppliedDiscount(String ruleName, BigDecimal amount) {}