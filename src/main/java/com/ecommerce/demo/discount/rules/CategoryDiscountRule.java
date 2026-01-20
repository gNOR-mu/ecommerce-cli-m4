package com.ecommerce.demo.discount.rules;

import com.ecommerce.demo.discount.DiscountRule;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.util.Constants;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Regla para los descuentos por categorías distintas
 */
public class CategoryDiscountRule implements DiscountRule {

    /**
     * {@inheritDoc}
     * Implementación específica que comprueba que la cantidad total de categorías sea mayor o igual
     * a {@value Constants#CATEGORIES_TRESHHOLD}
     */
    @Override
    public boolean isApplicable(Cart cart) {
        long differentCategories = cart.getAllItems().stream()
                .map(item -> item.getProduct().getCategoryId())
                .filter(Objects::nonNull)
                .distinct()
                .count();
        System.out.println(differentCategories);
        return differentCategories >= Constants.CATEGORIES_TRESHHOLD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculateDiscount() {
        return Constants.CATEGORIES_DISCOUNT_AMMOUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Descuento por llevar "+ Constants.CATEGORIES_TRESHHOLD+" categorías distintas";
    }
}
