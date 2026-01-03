package main.service;

import main.model.Product;
import main.repository.ProductRepository;

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
}
