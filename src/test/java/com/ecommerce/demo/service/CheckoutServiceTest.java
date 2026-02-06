package com.ecommerce.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.demo.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.Product;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {
    @Mock
    private DiscountCalculatorService discountCalculatorService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private CheckoutService service;

    @Test
    @DisplayName("Checkout: Obtiene el resumen")
    void testGetSummary_EmptyCart_ThrowsException() {
        // arrange
        BigDecimal subtotal = BigDecimal.ONE;
        int quantity = 1;
        Product p1 = new Product(1L, subtotal, "test");
        p1.setId(1L);

        Cart cart = new Cart();
        cart.addProduct(p1, 1);

        CartProductsDto cartProductsDto = new CartProductsDto(
                p1.getId(),
                p1.getName(),
                p1.getPrice(),
                quantity,
                subtotal
        );

        // sin descuentos
        DiscountSummary discountSummary = new DiscountSummary(List.of(), BigDecimal.ZERO);
        CartSummary cartSummary = new CartSummary(List.of(cartProductsDto), subtotal);

        when(cartService.getAll(cart)).thenReturn(cartSummary);
        when(discountCalculatorService.applyDiscount(cart)).thenReturn(discountSummary);

        // act
        CheckoutSummaryDto result = service.getSummary(cart);

        // assert
        assertEquals(0, subtotal.compareTo(result.finalPrice()));
        assertEquals(discountSummary, result.discountSummary());
        assertEquals(cartSummary, result.cartSummary());

        verify(cartService).getAll(cart);
        verify(discountCalculatorService).applyDiscount(cart);
    }

    @Test
    @DisplayName("Checkout: Debe aplicar descuento")
    void testGetSummary_validCartWithDiscount_applyDiscount() {
        // arrange
        BigDecimal subtotal = new BigDecimal("100000");
        //10% de descuento aplicado
        BigDecimal expectedTotal = new BigDecimal("90000");
        int quantity = 1;
        Product p1 = new Product(1L, subtotal, "test");
        p1.setId(1L);

        Cart cart = new Cart();
        cart.addProduct(p1, 1);

        CartProductsDto cartProductsDto = new CartProductsDto(
                p1.getId(),
                p1.getName(),
                p1.getPrice(),
                quantity,
                subtotal
        );
        AppliedDiscount appliedDiscount = new AppliedDiscount("test", "ninguna", new BigDecimal("10"));
        List<AppliedDiscount> discounts = List.of(appliedDiscount);

        CartSummary cartSummary = new CartSummary(List.of(cartProductsDto), subtotal);
        DiscountSummary discountSummary = new DiscountSummary(discounts, appliedDiscount.amount());

        when(cartService.getAll(cart)).thenReturn(cartSummary);
        when(discountCalculatorService.applyDiscount(cart)).thenReturn(discountSummary);

        // act
        CheckoutSummaryDto result = service.getSummary(cart);

        // assert
        assertEquals(0, expectedTotal.compareTo(result.finalPrice()));
        assertEquals(0, appliedDiscount.amount().compareTo(result.discountSummary().totalDiscount()));
        assertEquals(discountSummary, result.discountSummary());
        assertEquals(cartSummary, result.cartSummary());

        verify(cartService).getAll(cart);
        verify(discountCalculatorService).applyDiscount(cart);
    }

}