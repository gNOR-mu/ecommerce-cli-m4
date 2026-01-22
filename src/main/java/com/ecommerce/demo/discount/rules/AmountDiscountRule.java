package com.ecommerce.demo.discount.rules;

import com.ecommerce.demo.discount.DiscountRule;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.util.Constants;

import java.math.BigDecimal;

/**
 * Regla para los descuentos por costo total del carrito
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class AmountDiscountRule implements DiscountRule {
    /**
     * {@inheritDoc}
     * Implementación específica que comprueba que el costo total del carro, pre descuentos, sea mayor o igual
     * a {@link Constants#TOTAL_AMOUNT_THRESHOLD}
     */
    @Override
    public boolean isApplicable(Cart cart) {
        BigDecimal total = cart.getTotal();
        return total.compareTo(Constants.TOTAL_AMOUNT_THRESHOLD) >= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculateDiscount() {
        return Constants.TOTAL_COST_AMOUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Descuento por valor a pagar";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCondition() {
        return "LLeva %s en valor total del carrito".formatted(Constants.TOTAL_AMOUNT_THRESHOLD.toPlainString());
    }
}
