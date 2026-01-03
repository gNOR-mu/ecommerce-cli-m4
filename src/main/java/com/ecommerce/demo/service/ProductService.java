package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductService implements ReadOnlyService<Product,Long> {
    //TODO averiguar nombre constante
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product create(Product product){
        return productRepository.save(product);
    }
}
