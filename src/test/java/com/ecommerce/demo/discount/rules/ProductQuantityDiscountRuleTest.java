package com.ecommerce.demo.discount.rules;

import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductQuantityDiscountRuleTest {

    private final ProductQuantityDiscountRule rule = new ProductQuantityDiscountRule();
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    @DisplayName("Regla de Descuento: Debe aplicar si la cantidad de productos es igual al umbral")
    void isApplicable_categoriesEqualsThreshold_returnsTrue() {
        // Arrange
        int exactThreshold = Constants.QUANTITY_THRESHOLD;
        cart.addProduct(new Product(1L, new BigDecimal("100"), "P"), exactThreshold);

        // Act
        boolean result = rule.isApplicable(cart);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Regla de Descuento: Debe aplicar si la cantidad de productos es mayor al umbral")
    void isApplicable_categoriesGreaterThreshold_returnsTrue() {
        // Arrange
        int overThreshold = Constants.QUANTITY_THRESHOLD + 1;
        cart.addProduct(new Product(1L, new BigDecimal("100"), "P"), overThreshold);

        // Act
        boolean result = rule.isApplicable(cart);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Regla de Descuento: Debe aplicar si la cantidad de varios productos es mayor al umbral")
    void isApplicable_variousCategoriesGreaterThreshold_returnsTrue() {
        // Arrange
        int belowThreshold = Constants.QUANTITY_THRESHOLD - 1;
        int amountToCrossThreshold = 2;

        cart.addProduct(new Product(1L, new BigDecimal("100"), "P"), belowThreshold);
        cart.addProduct(new Product(2L, new BigDecimal("100"), "P2"), amountToCrossThreshold);

        // Act
        boolean result = rule.isApplicable(cart);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Regla de Descuento: No Debe aplicar si la cantidad de productos es inferior al umbral")
    void isApplicable_categoriesLowerThreshold_returnsFalse() {
        // Arrange
        int belowThreshold = Constants.QUANTITY_THRESHOLD - 1;
        cart.addProduct(new Product(1L, new BigDecimal("100"), "P"), belowThreshold);

        // Act
        boolean result = rule.isApplicable(cart);

        // Assert
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
    void calculateDiscount_amount_returnsTotalCostAmount() {
        // Act
        BigDecimal discount = rule.calculateDiscount();

        // Assert
        assertEquals(Constants.QUANTITY_DISCOUNT_AMOUNT, discount);
    }

}