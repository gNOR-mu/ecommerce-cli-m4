package com.ecommerce.demo.service;

import com.ecommerce.demo.exceptions.InvalidOperationException;
import com.ecommerce.demo.exceptions.ResourceAlreadyExistsException;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.repository.CategoryRepository;

import java.util.List;

/**
 * Servicio para la gestión de categorías
 *
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class CategoryService implements IdentifiableService<Category, Long> {
    private final CategoryRepository categoryRepository;

    /**
     * Constructor de la clase
     *
     * @param categoryRepository Repositorio de las categorías
     */
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category getById(Long id)  {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean notExistsById(Long id) {
        return !categoryRepository.existsById(id);
    }

    /**
     * Obtiene todas las categorías disponibles.
     *
     * @return Una lista con todas las categorías.
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Crea una nueva categoría.
     *
     * @param category Categoría a crear.
     * @return La categoría creada
     *
     * @throws InvalidOperationException Si el nombre está en blanco o es nulo
     * @throws ResourceAlreadyExistsException Si ya existe una categoría con ese nombre
     *
     */
    public Category create(Category category) {
        if (category.getName() == null || category.getName().isBlank()) {
            throw new InvalidOperationException("El nombre no puede estar en blanco");
        }
        if (categoryRepository.existsByName(category.getName())) {
            throw new ResourceAlreadyExistsException("Categoría", "nombre", category.getName());
        }
        return categoryRepository.save(category);
    }

    /**
     * Busca las ids que contienen un nombre determinado
     *
     * @param name Nombre a buscar
     * @return Listado con las ids de las categorías coincidentes.
     */
    public List<Long> findIdsByName(String name) {
        return categoryRepository.findIdsByName(name);
    }

}
