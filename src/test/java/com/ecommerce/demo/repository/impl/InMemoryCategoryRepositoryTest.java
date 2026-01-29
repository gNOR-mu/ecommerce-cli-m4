package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCategoryRepositoryTest {
    private InMemoryCategoryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryCategoryRepository();
    }

    @Test
    @DisplayName("Búsqueda: Debe retornar un listado con las ids coincidentes por el nombre ")
    void findIdsByName_partialText_returnMatchingIdsOnly() {
        // arrange
        String searchText = "asa";

        Category c1 = repository.save(new Category("casa"));
        Category c2 = repository.save(new Category("pasador"));

        repository.save(new Category("cristal"));

        List<Long> expectedIds = List.of(
                c1.getId(),
                c2.getId()
        );

        // act
        List<Long> resultIds = repository.findIdsByName(searchText);

        assertEquals(2, resultIds.size());
        assertTrue(resultIds.containsAll(expectedIds));

    }
}