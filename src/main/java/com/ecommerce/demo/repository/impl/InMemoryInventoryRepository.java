package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.repository.InventoryRepository;

public class InMemoryInventoryRepository extends InMemoryAbstractRepository<Inventory, Long> implements InventoryRepository {
}
