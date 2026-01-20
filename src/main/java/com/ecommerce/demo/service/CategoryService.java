package com.ecommerce.demo.service;

import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.repository.CategoryRepository;

import java.util.List;

/**
 * Servicio para la gestión de categorías
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class CategoryService implements IdentifiableService<Category, Long> {
    private final CategoryRepository CATEGORY_REPOSITORY;

    /**
     * Constructor de la clase
     * @param categoryRepository Repositorio de las categorías
     */
    public CategoryService(CategoryRepository categoryRepository) {
        this.CATEGORY_REPOSITORY = categoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category getById(Long id) throws ResourceNotFoundException {
        return CATEGORY_REPOSITORY.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean notExistsById(Long id) {
        return !CATEGORY_REPOSITORY.existsById(id);
    }

    /**
     * Obtiene todas las categorías disponibles.
     * @return Una lista con todas las categorías.
     */
    public List<Category> findAll() {
        return CATEGORY_REPOSITORY.findAll();
    }

    /**
     * Crea una nueva categoría.
     * @param category Categoría a crear.
     * @return La categoría creada
     */
    public Category create(Category category) {
        //TODO debería lanzar una excepción si ya existe
        return CATEGORY_REPOSITORY.save(category);
    }

    /**
     * Busca las ids que contienen un nombre determinado
     * @param name Nombre a buscar
     * @return Listado con las ids de las categorías coincidentes.
     */
    public List<Long> findIdsByName(String name) {
        return CATEGORY_REPOSITORY.findIdsByName(name);
    }
}
