package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.repository.InventoryRepository;

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
        return inventoryRepository.save(inventory);
    }

    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }
}
