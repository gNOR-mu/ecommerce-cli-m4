package main.repository.impl;

import main.model.Inventory;
import main.repository.InventoryRepository;

public class InMemoryInventoryRepository extends InMemoryAbstractRepository<Inventory, Long> implements InventoryRepository {
}
