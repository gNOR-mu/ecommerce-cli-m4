package com.ecommerce.demo.service;

import com.ecommerce.demo.exceptions.InvalidOperationException;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.repository.InventoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
    @Mock
    private InventoryRepository repository;

    @InjectMocks
    private InventoryService service;

    @Test
    @DisplayName("Obtención: Debe retornar el inventario cuando la id del producto existe")
    void getById_categoryId_returnsCategory() {
        // arrange
        Long productId = 1L;
        Inventory inventory = new Inventory(productId, 100);
        when(repository.findByProductId(productId)).thenReturn(Optional.of(inventory));

        // act
        Inventory obtained = service.getByProductId(productId);

        // assert
        assertEquals(inventory, obtained);
        verify(repository).findByProductId(productId);
    }

    @Test
    @DisplayName("Obtención: Debe lanzar ResourceNotFoundException cuando la id del producto no existe")
    void getByProductId_nonExistentIdProductId_throwsResourceNotFoundException() {
        //arrange
        Long nonExistentId = -100000L;
        when(repository.findByProductId(nonExistentId)).thenReturn(Optional.empty());

        // act & assert
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getByProductId(nonExistentId));
        assertEquals("No se ha encontrado un inventario correspondiente a un producto con id : -100000", exception.getMessage());
        verify(repository).findByProductId(nonExistentId);
    }

    @Test
    @DisplayName("Inventario: Debe reducir el inventario")
    void subtractInventory_validQuantity_reduceInventory() {
        //arrange
        Long productId = 1L;
        int inventoryQuantity = 100;
        int quantityToReduce = 50;
        int expectedQuantity = 50;

        Inventory inventory = new Inventory(productId, inventoryQuantity);

        when(repository.findByProductId(productId)).thenReturn(Optional.of(inventory));

        // act
        service.subtractInventory(productId, quantityToReduce);

        //assert
        assertEquals(expectedQuantity, inventory.getQuantity());
        assertTrue(inventory.getQuantity() >= 0);
    }

    @Test
    @DisplayName("Inventario: No debe poder reducir el inventario cuando el resultado es negativo")
    void subtractInventory_invalidQuantity_throwsInvalidOperationException() {
        //arrange
        Long productId = 1L;
        int quantityToReduce = 10;
        int originalQuantity = 1;

        Inventory inventory = new Inventory(productId, originalQuantity);

        when(repository.findByProductId(productId)).thenReturn(Optional.of(inventory));

        // act y assert
        Exception exception = assertThrows(InvalidOperationException.class,
                () -> service.subtractInventory(productId, quantityToReduce));

        assertEquals("No puede haber un stock negativo", exception.getMessage());
        assertEquals(originalQuantity, inventory.getQuantity());
    }

    @Test
    @DisplayName("Sustracción: Es posible sustraer con cantidad válida")
    void isPossibleSubtract_validQuantity_returnsTrue() {
        // arrange
        Long productId = 1L;
        int productQuantity = 100;
        int subtractQuantity = 5;
        Inventory inventory = new Inventory(productId, productQuantity);

        when(repository.findByProductId(productId)).thenReturn(Optional.of(inventory));

        // act
        boolean result = service.isPossibleSubtract(productId, subtractQuantity);

        // assert
        assertTrue(result);
        verify(repository).findByProductId(productId);
    }

    @Test
    @DisplayName("Sustracción: No es posible sustraer con cantidad inválida")
    void isPossibleSubtract_invalidQuantity_returnsFalse() {
        // arrange
        Long productId = 1L;
        int productQuantity = 5;
        int subtractQuantity = 5000;
        Inventory inventory = new Inventory(productId, productQuantity);

        when(repository.findByProductId(productId)).thenReturn(Optional.of(inventory));

        // act
        boolean result = service.isPossibleSubtract(productId, subtractQuantity);

        // assert
        assertFalse(result);
        verify(repository).findByProductId(productId);
    }


}