package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;

import java.util.List;
import java.util.Map;

/**
 * Implementación en memoria del repositorio de productos.
 * <p>
 * Utiliza un {@link java.util.HashMap} para simular la persistencia de datos.
 * Esta implementación no es persistente: los datos se perderán al reiniciar la aplicación.
 * </p>
 *
 */
public class InMemoryProductRepository extends InMemoryAbstractRepository<Product> implements ProductRepository {

    /**
     * {@inheritDoc}
     * <p>
     * <b>Nota de implementación:</b>
     * Realiza un filtrado secuencial sobre todos los elementos en memoria.
     * Se retorna una lista vacía si name es null
     * </p>
     */
    @Override
    public List<Product> findByNameIgnoreCase(String name) {
        if (name == null){
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
