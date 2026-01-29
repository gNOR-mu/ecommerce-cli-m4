package com.ecommerce.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    @DisplayName("Carrito: Debe añadir el mismo producto que se ha seleccionado")
    void addProduct_newProduct_addToCart() {
        // Arrange
        Product product = new Product(1L, BigDecimal.ONE, "P1");
        int quantity = 1;

        // act
        cart.addProduct(product, quantity);

        // assert
        assertEquals(1, cart.getAllItems().size());

        Optional<CartItem> addedItem = cart.getCartItem(product.getId());
        assertTrue(addedItem.isPresent());

        CartItem item = addedItem.get();
        assertEquals(quantity, item.getQuantity());
        assertEquals(product, item.getProduct());
    }

    @Test
    @DisplayName("Carrito: Debe aumentar la cantidad del producto si ya se encuentra en el carrito")
    void addProduct_existingProduct_incrementQuantity() {
        // arrange
        Product product = new Product(1L, BigDecimal.ONE, "P1");
        int firstQuantity = 1;
        cart.addProduct(product, firstQuantity);

        // act
        int secondQuantity = 2;
        cart.addProduct(product, secondQuantity);

        int totalQuantity = firstQuantity + secondQuantity;

        // assert
        Optional<CartItem> added = cart.getCartItem(product.getId());
        assertTrue(added.isPresent());
        assertEquals(totalQuantity, added.get().getQuantity());
    }

    @Test
    @DisplayName("Carrito: Debe limpiar el carrito")
    void  clear_nonEmptyCar_clearAllItems(){
        // arrange
        Product product = new Product(1L, BigDecimal.ONE, "P1");
        cart.addProduct(product, 10);

        // act
        cart.clear();

        // assert
        assertEquals(0, cart.getAllItems().size());
    }

    @Test
    @DisplayName("Carrito: Debe remover un producto del carrito")
    void removeItem_existingProduct_removeFromCart(){
        // arrange
        Product product = new Product(1L, BigDecimal.ONE, "P1");
        cart.addProduct(product, 10);

        // act
        cart.removeItem(product.getId());

        // assert
        Optional<CartItem> added = cart.getCartItem(product.getId());
        assertFalse(added.isPresent());
    }

    @Test
    @DisplayName("Carrito: Debe remover solo el producto especificado")
    void removeItem_specificProduct_preservesOtherProducts(){
        // arrange
        Product product = new Product(1L, BigDecimal.ONE, "P1");
        product.setId(1L);

        Product product2 = new Product(2L, BigDecimal.ONE, "P2");
        product2.setId(2L);

        cart.addProduct(product, 10);
        cart.addProduct(product2, 20);

        // act
        cart.removeItem(product.getId());

        // assert
        Optional<CartItem> added = cart.getCartItem(product.getId());
        assertFalse(added.isPresent());
        assertEquals(1, cart.getAllItems().size());
    }
}