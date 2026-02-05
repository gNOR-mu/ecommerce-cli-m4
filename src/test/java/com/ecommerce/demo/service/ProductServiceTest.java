package com.ecommerce.demo.service;

import com.ecommerce.demo.exceptions.InvalidOperationException;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService service;

    static Stream<Named<Product>> invalidProductNames() {
        return Stream.of(
                Named.of("Nombre con espacios en blanco", new Product(1L, BigDecimal.ONE, " ")),
                Named.of("Nombre vacío", new Product(1L, BigDecimal.ONE, "")),
                Named.of("Nombre nulo", new Product(1L, BigDecimal.ONE, null))
        );
    }

    static Stream<Named<Product>> invalidPrices() {
        return Stream.of(
                Named.of("Precio negativo", new Product(1L, new BigDecimal("-100"), "test")),
                Named.of("Precio 0", new Product(1L, BigDecimal.ZERO, "test"))
        );
    }

    @Test
    @DisplayName("Obtención: Debe retornar el producto cuando la id existe")
    void getById_productId_returnsProduct() {
        // arrange
        Long productId = 1L;
        Product product = new Product(productId, BigDecimal.ONE, "test");

        when(repository.findById(productId)).thenReturn(Optional.of(product));

        // act
        Product obtained = service.getById(productId);

        // assert
        assertEquals(product, obtained);
        verify(repository).findById(productId);
    }

    @Test
    @DisplayName("Obtención: Debe lanzar ResourceNotFoundException cuando la id no existe")
    void getById_nonExistingProductId_throwsResourceNotFoundException() {
        // arrange
        Long nonExistingId = -10000L;
        Product product = new Product(nonExistingId, BigDecimal.ONE, "test");

        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        // act & assert
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getById(nonExistingId));
        assertEquals("Producto no encontrado con id : -10000", exception.getMessage());
        verify(repository).findById(nonExistingId);
    }

    @Test
    @DisplayName("Creación: Debe lanzar ResourceNotFoundException cuando la categoría no existe")
    void create_nonExistingCategory_failsProductCreation() {
        // arrange
        Long categoryId = 0L;
        int productQuantity = 1000;
        Product product = new Product(categoryId, BigDecimal.ONE, "test");

        doThrow(new ResourceNotFoundException("Categoría", categoryId))
                .when(categoryService).getById(categoryId);

        // act & assert
        Exception exception = assertThrows(ResourceNotFoundException.class,
                () -> service.create(product, productQuantity));

        assertEquals("Categoría no encontrado con id : 0", exception.getMessage());

        verify(repository, never()).save(any());
        verify(inventoryService, never()).create(any());
        verify(categoryService).getById(categoryId);
    }


    @DisplayName("Creación: Nombre inválido no debe crear producto ni inventario")
    @ParameterizedTest(name = "{0} No debe crear el producto ni inventario")
    @MethodSource("invalidProductNames")
    void create_invalidPrice_failsProductCreation(Product product) {
        // arrange
        int quantity = 100;

        // act & assert
        Exception exception = assertThrows(InvalidOperationException.class, () -> service.create(product, quantity));

        assertEquals("El nombre del producto es inválido", exception.getMessage());
        verify(repository, never()).save(product);
        verify(inventoryService, never()).create(any());
    }

    @DisplayName("Creación: Precio inválido no debe crear producto ni inventario")
    @ParameterizedTest(name = "{0} No debe crear el producto ni inventario")
    @MethodSource("invalidPrices")
    void create_invalidName_failsProductCreation(Product product) {
        // arrange
        int quantity = 100;

        // act & assert
        Exception exception = assertThrows(InvalidOperationException.class, () -> service.create(product, quantity));

        assertEquals("El precio no puede ser <= 0", exception.getMessage());
        verify(repository, never()).save(product);
        verify(inventoryService, never()).create(any());
    }

    @Test
    @DisplayName("Creación: Debe lanzar InvalidOperationException cuando el inventario es < 0")
    void create_invalidInventory_thorsInvalidOperationException() {
        // arrange
        int inventoryQuantity = -10;
        Category category = new Category("test");
        Product product = new Product(0L, BigDecimal.ONE, "test");

        when(repository.save(any())).thenReturn(product);

        doThrow(new InvalidOperationException("El inventario no puede ser inferior a 0"))
                .when(inventoryService).create(any());

        when(categoryService.getById(any())).thenReturn(category);

        // act & assert
        Exception exception = assertThrows(InvalidOperationException.class,
                () -> service.create(product, inventoryQuantity));

        assertEquals("El inventario no puede ser inferior a 0", exception.getMessage());

        verify(inventoryService).create(any());
        verify(repository).save(any());

        verify(repository).deleteById(product.getId());

    }

    @Test
    @DisplayName("Creación: Debe crear un producto cuando los datos son válidos")
    void create_validProduct_createProductAndInventory() {
        // arrange
        int stock = 1000;
        Product product = new Product(1L, BigDecimal.ONE, "test");

        Product ret = new Product(1L, BigDecimal.ONE, "test");
        ret.setId(1L);

        Category category = new Category("test");
        category.setId(1L);

        when(repository.save(product)).thenReturn(ret);
        when(categoryService.getById(any())).thenReturn(category);

        // act
        Product result = service.create(product, stock);

        // assert
        assertNotNull(result);
        assertNotNull(result.getId());

        assertEquals(product.getName(), result.getName());
        assertEquals(product.getCategoryId(), result.getCategoryId());
        assertEquals(product.getPrice(), result.getPrice());

        verify(inventoryService).create(any());
        verify(repository).save(product);
        verify(repository, never()).deleteById(any());

    }
}