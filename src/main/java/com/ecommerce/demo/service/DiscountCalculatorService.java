package com.ecommerce.demo.service;

import com.ecommerce.demo.discount.DiscountRule;
import com.ecommerce.demo.dto.AppliedDiscount;
import com.ecommerce.demo.dto.DiscountSummary;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.util.Constants;

import java.math.BigDecimal;
import java.util.List;

public class DiscountCalculatorService {

    public DiscountSummary applyDiscount(Cart cart) {
        List<AppliedDiscount> discounts = Constants.ACTIVE_RULES.stream()
                .filter(rule -> rule.isApplicable(cart))
                .map(rule -> new AppliedDiscount(rule.getName(),rule.calculateDiscount()))
                .toList();

        //total de descuentos
        BigDecimal totalOff = discounts.stream()
                .map(AppliedDiscount::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalOff.compareTo(cart.getTotal()) > 0) {
            totalOff = cart.getTotal();
        }
        System.out.println(discounts);
        return new DiscountSummary(discounts,totalOff);
    }
}
