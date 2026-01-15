package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryService implements ReadOnlyService<Category, Long> {
    private final CategoryRepository CATEGORY_REPOSITORY;

    public CategoryService(CategoryRepository categoryRepository) {
        this.CATEGORY_REPOSITORY = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return CATEGORY_REPOSITORY.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return CATEGORY_REPOSITORY.existsById(id);
    }

    public List<Category> findAll() {
        return CATEGORY_REPOSITORY.findAll();
    }

    public Category create(Category category) {
        return CATEGORY_REPOSITORY.save(category);
    }

    public List<Long> findIdsByName(String name) {
        return CATEGORY_REPOSITORY.findIdsByName(name);
    }
}
