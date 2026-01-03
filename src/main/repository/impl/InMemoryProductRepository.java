package main.repository.impl;

import main.model.Product;
import main.repository.ProductRepository;

public class InMemoryProductRepository extends InMemoryAbstractRepository<Product, Long> implements ProductRepository {
}
