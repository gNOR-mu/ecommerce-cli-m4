package main.service;

import main.model.Inventory;
import main.repository.InventoryRepository;

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
