package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.repository.InventoryRepository;

import java.util.List;
import java.util.Optional;

public class InventoryService implements ReadOnlyService<Inventory, Long> {
    //TODO averiguar nombre constante
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return inventoryRepository.existsById(id);
    }


    public Inventory create(Inventory inventory) {
        if (inventory.getQuantity() < 0) {
            throw new IllegalArgumentException("El inventario no puede ser inferior a 0");
        }
        return inventoryRepository.save(inventory);
    }

    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }

    public Inventory getByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId).orElseThrow(
                () -> new IllegalArgumentException("No se ha encontrado un inventario con el producto id = " + productId));
    }

    public Inventory updateByProductId(Long productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("El inventario no puede ser inferior a 0");
        }
        Inventory existing = getByProductId(productId);
        existing.setQuantity(quantity);
        return inventoryRepository.save(existing);
    }
}
