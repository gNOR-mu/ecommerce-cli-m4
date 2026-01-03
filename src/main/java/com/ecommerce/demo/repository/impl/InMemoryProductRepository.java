package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;

public class InMemoryProductRepository extends InMemoryAbstractRepository<Product, Long> implements ProductRepository {
}
