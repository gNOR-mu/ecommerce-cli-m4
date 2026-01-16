package com.ecommerce.demo.discount.rules;

import com.ecommerce.demo.discount.DiscountRule;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.CartItem;
import com.ecommerce.demo.util.Constants;

import java.math.BigDecimal;
import java.util.Objects;

public class QuantityDiscountRule implements DiscountRule {

    /**
     * {@inheritDoc}
     * Implementación específica que comprueba que la cantidad total de productos sea mayor o igual
     * a {@value Constants#QUANTITY_TRESHHOLD}
     */
    @Override
    public boolean isApplicable(Cart cart) {
        int quantity = cart.getAllItems().stream()
                .mapToInt(CartItem::getQuantity)
                .filter(Objects::nonNull)
                .sum();
        return quantity >= Constants.QUANTITY_TRESHHOLD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculateDiscount() {
        return Constants.QUANTITY_DISCOUNT_AMMOUNT;
    }

    @Override
    public String getName() {
        return "Descuento sobre " + Constants.QUANTITY_TRESHHOLD + " productos: " + Constants.QUANTITY_DISCOUNT_AMMOUNT + "%";
    }
}
