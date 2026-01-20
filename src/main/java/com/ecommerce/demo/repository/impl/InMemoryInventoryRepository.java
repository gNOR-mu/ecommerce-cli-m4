package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.repository.InventoryRepository;

import java.util.Optional;

/**
 * Implementación en memoria de {@link InventoryRepository}
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class InMemoryInventoryRepository extends InMemoryAbstractRepository<Inventory> implements InventoryRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Inventory> findByProductId(Long productId) {
        return DB.values().stream().filter(inventory -> inventory.getProductId().equals(productId)).findFirst();
    }
}
