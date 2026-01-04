package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.repository.InventoryRepository;

import java.util.Optional;

public class InMemoryInventoryRepository extends InMemoryAbstractRepository<Inventory> implements InventoryRepository {

    @Override
    public Optional<Inventory> findByProductId(Long productId) {
        return DB.values().stream().filter(inventory -> inventory.getProductId().equals(productId)).findFirst();
    }
}
