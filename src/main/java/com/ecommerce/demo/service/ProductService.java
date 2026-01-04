package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;

import java.util.ArrayList;
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

    public List<ProductSummaryDto> findAllSummary() {
        List<Product> products = productRepository.findAll();
        List<ProductSummaryDto> res = new ArrayList<>();

        //NOTA no es la forma más óptima, sin embargo, el rendimiento no es un requisito
        for (Product product : products) {
            var category = categoryService.getById(product.getCategoryId());
            var inventory = inventoryService.getByProductId(product.getId());
            res.add(new ProductSummaryDto(product.getId(), product.getName(), category.getName(), product.getPrice(), inventory.getQuantity()));
        }

        return res;

    }


    public void deleteById(Long id) {
        Product product = getById(id);
        productRepository.deleteById(id);
        inventoryService.deleteById(product.getCategoryId());
    }
}
