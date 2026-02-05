package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.repository.CategoryRepository;

import java.util.List;

/**
 * Implementación en memoria de {@link CategoryRepository}
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class InMemoryCategoryRepository extends InMemoryAbstractRepository<Category> implements CategoryRepository {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> findIdsByName(String searchText) {
        String searchLower = searchText.toLowerCase();
        return database.values().stream()
                .filter(c -> c.getName().toLowerCase().contains(searchLower))
                .map(Category::getId)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByName(String name) {
        return database.values().stream()
                .anyMatch(category -> category.getName().equalsIgnoreCase(name));
    }
}
