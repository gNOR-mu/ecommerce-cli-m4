package com.ecommerce.demo.discount.rules;

import com.ecommerce.demo.discount.DiscountRule;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.CartItem;
import com.ecommerce.demo.util.Constants;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Regla para los descuentos por cantidad de productos
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class ProductQuantityDiscountRule implements DiscountRule {

    /**
     * {@inheritDoc}
     * Implementación específica que comprueba que la cantidad total de productos sea mayor o igual
     * a {@value Constants#QUANTITY_THRESHOLD}
     */
    @Override
    public boolean isApplicable(Cart cart) {
        int quantity = cart.getAllItems().stream()
                .mapToInt(CartItem::getQuantity)
                .filter(Objects::nonNull)
                .sum();
        return quantity >= Constants.QUANTITY_THRESHOLD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculateDiscount() {
        return Constants.QUANTITY_DISCOUNT_AMOUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Descuento por cantidad de productos";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCondition() {
        return "LLeva %d productos".formatted(Constants.QUANTITY_THRESHOLD);
    }
}
