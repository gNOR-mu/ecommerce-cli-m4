package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryService implements ReadOnlyService<Category, Long> {
    //TODO averiguar nombre constante
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

}
