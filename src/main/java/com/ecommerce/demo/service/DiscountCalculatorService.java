package com.ecommerce.demo.service;

import com.ecommerce.demo.discount.DiscountRule;
import com.ecommerce.demo.dto.AppliedDiscount;
import com.ecommerce.demo.dto.DiscountSummary;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.util.Constants;

import java.math.BigDecimal;
import java.util.List;

/**
 * Servicio para la gestión de descuentos
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class DiscountCalculatorService {

    /**
     * Aplica el descuento basado en las reglas activas.
     * @param cart Carrito sobre el cual aplicar los descuentos
     * @return Un resumen con los descuentos
     *
     * @see Constants#ACTIVE_RULES
     * @see com.ecommerce.demo.discount.DiscountRule
     * @see DiscountSummary
     */
    public DiscountSummary applyDiscount(Cart cart) {
        List<AppliedDiscount> discounts = Constants.ACTIVE_RULES.stream()
                .filter(rule -> rule.isApplicable(cart))
                .map(rule -> new AppliedDiscount(rule.getName(), rule.getCondition(), rule.calculateDiscount()))
                .toList();

        //total de descuentos
        BigDecimal totalOff = discounts.stream()
                .map(AppliedDiscount::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalOff.compareTo(cart.getTotal()) > 0) {
            totalOff = cart.getTotal();
        }
        return new DiscountSummary(discounts,totalOff);
    }
}
