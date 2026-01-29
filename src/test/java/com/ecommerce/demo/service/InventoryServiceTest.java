package com.ecommerce.demo.service;

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
    }

    @Test
    @DisplayName("Obtención: Debe lanzar ResourceNotFoundException cuando la id del producto no existe")
    void getById_nonExistentIdProductId_throwsResourceNotFoundException() {
        //arrange
        Long nonExistentId = Long.MIN_VALUE;

        //assert
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getByProductId(nonExistentId));
        assertEquals("No se ha encontrado un inventario correspondiente a un producto con id : " + nonExistentId, exception.getMessage());
    }

    @Test
    @DisplayName("Inventario: Debe reducir el inventario")
    void subtractInventory_validQuantity_reduceInventory() {
        //arrange
        Long productId = 1L;
        int quantityToReduce = 10;
        int inventoryQuantity = 1000;
        int expectedQuantity = inventoryQuantity - quantityToReduce;

        Inventory inventory = new Inventory(productId, inventoryQuantity);

        when(repository.findByProductId(productId)).thenReturn(Optional.of(inventory));

        // act
        service.subtractInventory(productId, quantityToReduce);

        //assert
        assertEquals(expectedQuantity, inventory.getQuantity());
    }

    @Test
    @DisplayName("Inventario: No debe poder reducir el inventario cuando el resultado es negativo")
    void subtractInventory_invalidQuantity_throwsIllegalArgumentException() {
        //arrange
        Long productId = 1L;
        int quantityToReduce = 10;
        int originalQuantity = 1;

        Inventory inventory = new Inventory(productId, originalQuantity);

        when(repository.findByProductId(productId)).thenReturn(Optional.of(inventory));

        // act y assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.subtractInventory(productId, quantityToReduce));

        assertEquals("No puede haber un stock negativo", exception.getMessage());
        assertEquals(originalQuantity, inventory.getQuantity());
    }


}