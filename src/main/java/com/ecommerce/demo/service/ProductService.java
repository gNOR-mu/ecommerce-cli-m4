package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.InventoryRepository;
import com.ecommerce.demo.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductService implements ReadOnlyService<Product, Long> {
    //TODO averiguar nombre constante
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final InventoryService inventoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, InventoryService inventoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.inventoryService = inventoryService;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(Product product, int quantity) {
        if (!categoryService.existsById(product.getCategoryId())) {
            throw new IllegalArgumentException("La categoría con id = " + product.getCategoryId() + " no existe");
        }
        //TODO Validación nombre nulo, vacío, y lo mismo para precio
        Product createdProduct = productRepository.save(product);

        Inventory inventory = new Inventory(createdProduct.getId(), quantity);
        inventoryService.create(inventory);

        return createdProduct;
    }


    public void deleteById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado un producto con la id = " + id));
        inventoryService.deleteById(product.getCategoryId());
        productRepository.deleteById(id);
    }
}
