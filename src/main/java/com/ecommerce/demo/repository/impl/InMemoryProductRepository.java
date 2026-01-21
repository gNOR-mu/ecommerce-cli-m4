package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;


/**
 * Implementación en memoria de {@link ProductRepository}
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class InMemoryProductRepository extends InMemoryAbstractRepository<Product> implements ProductRepository {

}
