package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.repository.InventoryRepository;

import java.util.Optional;

public class InventoryService implements ReadOnlyService<Inventory, Long> {
    //TODO averiguar nombre constante
    private final InventoryRepository INVENTORY_REPOSITORY;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.INVENTORY_REPOSITORY = inventoryRepository;
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return INVENTORY_REPOSITORY.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return INVENTORY_REPOSITORY.existsById(id);
    }


    public Inventory create(Inventory inventory) {
        if (inventory.getQuantity() < 0) {
            throw new IllegalArgumentException("El inventario no puede ser inferior a 0");
        }
        return INVENTORY_REPOSITORY.save(inventory);
    }

    public void deleteById(Long id) {
        INVENTORY_REPOSITORY.deleteById(id);
    }

    public Inventory getByProductId(Long productId) {
        return INVENTORY_REPOSITORY.findByProductId(productId).orElseThrow(
                () -> new IllegalArgumentException("No se ha encontrado un inventario con el producto id = " + productId));
    }

    public Inventory updateByProductId(Long productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("El inventario no puede ser inferior a 0");
        }
        Inventory existing = getByProductId(productId);
        existing.setQuantity(quantity);
        return INVENTORY_REPOSITORY.save(existing);
    }
}
