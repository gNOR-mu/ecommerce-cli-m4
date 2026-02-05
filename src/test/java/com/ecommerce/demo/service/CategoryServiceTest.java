package com.ecommerce.demo.service;

import com.ecommerce.demo.exceptions.InvalidOperationException;
import com.ecommerce.demo.exceptions.ResourceAlreadyExistsException;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    @Test
    @DisplayName("Obtención: Debe retornar la categoría cuando la id existe")
    void getById_categoryId_returnsCategory() {
        // arrange
        Long id = 1L;
        Category category = new Category("Test");
        category.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(category));

        // act
        Category obtained = service.getById(category.getId());

        // assert
        assertEquals(category, obtained);

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Obtención: Debe lanzar excepción cuando la id no existe")
    void getById_categoryId_throwsResourceNotFoundException() {
        // arrange
        Long id = Long.MIN_VALUE;
        String expectedMsg = "%s no encontrado con id : %d".formatted("Categoría", id);

        // act & assert
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getById(Long.MIN_VALUE));

        assertEquals(expectedMsg, exception.getMessage());

        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Obtención: Debe retornar todas las categorías")
    void findAll_existingCategories_returnsAllCategories() {
        // arrange
        List<Category> expectedCategories = List.of(
                new Category("C1"),
                new Category("C2"),
                new Category("C3")
        );

        when(repository.findAll()).thenReturn(expectedCategories);

        // act
        List<Category> obtained = service.findAll();

        // assert
        assertTrue(obtained.containsAll(expectedCategories));

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Creación: Debe lanzar InvalidOperationException con un nombre inválido")
    void create_invalidCategoryName_throwsInvalidOperationException() {
        // arrange
        Category category = new Category(null);

        // act & assert
        Exception exception = assertThrows(InvalidOperationException.class, () -> service.create(category));
        assertEquals("El nombre no puede estar en blanco", exception.getMessage());

        verify(repository, never()).save(category);
    }

    @Test
    @DisplayName("Creación: Debe lanzar ResourceAlreadyExistsException si ya existe una categoría con ese nombre")
    void create_existingCategoryName_throwsResourceAlreadyExistsException() {
        // arrange
        String name = "test";
        Category category = new Category(name);
        when(repository.existsByName(anyString())).thenReturn(true);

        // act & assert
        Exception exception = assertThrows(ResourceAlreadyExistsException.class, () -> service.create(category));

        assertEquals("Categoría con nombre 'test' ya existe", exception.getMessage());

        verify(repository).existsByName(name);
        verify(repository, never()).save(category);
    }

}