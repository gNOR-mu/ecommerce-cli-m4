package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.repository.CategoryRepository;

import java.util.List;

public class InMemoryCategoryRepository extends InMemoryAbstractRepository<Category> implements CategoryRepository {
    public List<Long> findIdsByName(String searchText) {
        String searchLower = searchText.toLowerCase();
        return DB.values().stream()
                .filter(c -> c.getName().toLowerCase().contains(searchLower))
                .map(Category::getId)
                .toList();
    }
}
