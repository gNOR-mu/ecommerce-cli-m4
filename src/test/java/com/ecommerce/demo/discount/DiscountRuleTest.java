package com.ecommerce.demo.discount;

import com.ecommerce.demo.discount.rules.TotalAmountDiscountRule;
import com.ecommerce.demo.discount.rules.ProductCategoryDiscountRule;
import com.ecommerce.demo.discount.rules.ProductQuantityDiscountRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DiscountRuleTest {
    /**
     * Todas las reglas del programa
     *
     * @return Instancia de todas las reglas existentes en el programa
     */
    static Stream<Named<DiscountRule>> provideAllRules() {
        return Stream.of(
                Named.of("Descuento por Monto total", new TotalAmountDiscountRule()),
                Named.of("Descuento por Cantidad de categorías", new ProductCategoryDiscountRule()),
                Named.of("Descuento por Cantidad de productos", new ProductQuantityDiscountRule())
        );
    }

    @ParameterizedTest(name = "Regla: {0} no debe retornar un descuento <= 0")
    @MethodSource("provideAllRules")
    @DisplayName("Los descuentos deben ser mayor a 0")
    void rule_calculateDiscount_returnsAboveZero(DiscountRule rule) {
        assertTrue(BigDecimal.ZERO.compareTo(rule.calculateDiscount()) <= 0);
    }

}