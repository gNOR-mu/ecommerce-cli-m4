package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;

import java.util.List;

/**
 * Implementación en memoria de {@link ProductRepository}
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class InMemoryProductRepository extends InMemoryAbstractRepository<Product> implements ProductRepository {

    /**
     * {@inheritDoc}
     * <p>
     * <b>Nota de implementación:</b>
     * Realiza un filtrado secuencial sobre todos los elementos en memoria.
     * Se retorna una lista vacía si name es null o isBlank es true
     * </p>
     */
    @Override
    public List<Product> findByNameIgnoreCase(String name) {
        if (name == null || name.isBlank()){
            return List.of();
        }
        return DB.values().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .toList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>Nota de implementación:</b>
     * Realiza un filtrado secuencial sobre todos los elementos en memoria.
     * Se retorna una lista vacía si categoryId es null
     * </p>
     */
    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        if (categoryId == null){
            return List.of();
        }
        return DB.values().stream()
                .filter(product -> product.getCategoryId().equals(categoryId))
                .toList();
    }
}
