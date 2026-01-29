package com.ecommerce.demo.discount;

import com.ecommerce.demo.discount.rules.AmountDiscountRule;
import com.ecommerce.demo.discount.rules.CategoryDiscountRule;
import com.ecommerce.demo.discount.rules.QuantityDiscountRule;
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
                Named.of("Descuento por Monto", new AmountDiscountRule()),
                Named.of("Descuento por Cantidad de categorías", new CategoryDiscountRule()),
                Named.of("Descuento por Cantidad de productos", new QuantityDiscountRule())
        );
    }

    @ParameterizedTest(name = "Regla: {0} no debe retornar un descuento <= 0>")
    @MethodSource("provideAllRules")
    void rule_calculateDiscount_returnsAboveZero(DiscountRule rule) {
        assertTrue(BigDecimal.ZERO.compareTo(rule.calculateDiscount()) <= 0);
    }

}