package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.repository.InventoryRepository;

import java.util.Optional;

public class InventoryService implements ReadOnlyService<Inventory,Long>{
    //TODO averiguar nombre constante
    private final InventoryRepository inventoryRepository;
    private final ProductService productService;

    public InventoryService(InventoryRepository inventoryRepository, ProductService productService) {
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }
}
