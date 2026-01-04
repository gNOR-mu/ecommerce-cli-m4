package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.Inventory;

import java.util.Optional;

public interface InventoryRepository extends CrudRepository<Inventory,Long> {
    Optional<Inventory> findByProductId(Long productId);
}
