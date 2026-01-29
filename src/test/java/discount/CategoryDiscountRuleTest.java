package discount;

import com.ecommerce.demo.discount.rules.CategoryDiscountRule;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la regla de descuento por monto {@link CategoryDiscountRuleTest}
 */
public class CategoryDiscountRuleTest {

    private Cart cart;

    private final CategoryDiscountRule rule = new CategoryDiscountRule();

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    @DisplayName("Regla de Descuento: Debe aplicar si la cantidad de categorías es igual al umbral")
    void isApplicable_categoriesEqualsThreshold_returnsTrue() {
        // Arrange
        addDifferentProductsToCart(Constants.CATEGORIES_THRESHOLD);

        // Act
        boolean result = rule.isApplicable(cart);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Regla de Descuento: Debe aplicar si la cantidad de categorías es mayor al umbral")
    void isApplicable_categoriesGreaterThreshold_returnsTrue() {
        // Arrange
        addDifferentProductsToCart(Constants.CATEGORIES_THRESHOLD + 1);

        // Act
        boolean result = rule.isApplicable(cart);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Regla de Descuento: No Debe aplicar si la cantidad de categorías es inferior al umbral")
    void isApplicable_categoriesLowerThreshold_returnsFalse() {
        // Arrange
        addDifferentProductsToCart(Constants.CATEGORIES_THRESHOLD - 1);

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
        assertEquals(Constants.CATEGORIES_DISCOUNT_AMOUNT, discount);
    }

    /**
     * Utilidad para la creación de distintos productos
     * @param productsQuantity Cantidad de productos a crear
     */
    private void addDifferentProductsToCart(long productsQuantity) {
        for (long i = 0; i < productsQuantity; i++) {
            String name = "category_" + i;
            //el precio es irrelevante para esta regla
            Product product = new Product(i, new BigDecimal("1"), name);
            product.setId(i);

            cart.addProduct(product, 1);
        }
    }
}
