package com.ecommerce.demo.service;

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

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    @DisplayName("Obtención: Debe lanzar excepción cuando la id no existe")
    void getById_categoryId_throwsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> service.getById(Long.MIN_VALUE));
    }

    @Test
    @DisplayName("Obtención: Debe retornar todas las categorías")
    void findAll_existingCategories_returnsAllCategories() {
        // arrange
        Category c1 = new Category("C1");
        Category c2 = new Category("C1");
        Category c3 = new Category("C1");

        List<Category> expectedCategories = List.of(c1, c2, c3);

        when(repository.findAll()).thenReturn(expectedCategories);

        // act
        List<Category> obtained = service.findAll();

        // assert
        assertTrue(obtained.containsAll(expectedCategories));
    }

}