package com.ecommerce.demo.discount.rules;

import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la regla de descuento por monto {@link AmountDiscountRule}
 */
public class AmountDiscountRuleTest {

    private Cart cart;

    private final AmountDiscountRule rule = new AmountDiscountRule();

    @BeforeEach
    void setUp(){
        cart  = new Cart();
    }

    @Test
    @DisplayName("Regla de Descuento: Debe aplicar si el monto es igual al umbral")
    void isApplicable_amountEqualsThreshold_returnsTrue(){
        cart.addProduct(new Product(1L, Constants.TOTAL_AMOUNT_THRESHOLD,"P1"), 1);

        assertTrue(rule.isApplicable(cart));
    }

    @Test
    @DisplayName("Regla de Descuento: Debe aplicar si el monto es mayor al umbral")
    void isApplicable_amountGreaterThreshold_returnsTrue(){
        BigDecimal amount = Constants.TOTAL_AMOUNT_THRESHOLD.add(new BigDecimal("10"));
        cart.addProduct(new Product(1L, amount,"P1"), 1);

        boolean result = rule.isApplicable(cart);
        assertTrue(result);
    }

    @Test
    @DisplayName("Regla de Descuento: No debe aplicar si el monto es menor al umbral")
    void isApplicable_amountLowerThreshold_returnsFalse(){
        BigDecimal amount = Constants.TOTAL_AMOUNT_THRESHOLD.subtract(new BigDecimal("10"));
        cart.addProduct(new Product(1L, amount,"P1"), 1);

        boolean result = rule.isApplicable(cart);
        assertFalse(result);
    }

    @Test
    @DisplayName("Regla de Descuento: No Debe aplicar si el carro está vacío")
    void isApplicable_emptyCart_returnsFalse() {
        // Act
        boolean result = rule.isApplicable(cart);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Regla de descuento: El descuento calculado debe ser el monto especificado")
    void calculateDiscount_amount_returnsTotalCostAmount(){
        BigDecimal discount = rule.calculateDiscount();
        assertEquals(Constants.TOTAL_COST_AMOUNT, discount);
    }
}
