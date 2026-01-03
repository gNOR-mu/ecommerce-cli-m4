package main.service;

import main.model.Category;
import main.repository.CategoryRepository;

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
}
