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
            res.add(new ProductSummaryDto(product.getId(), product.getName(), category.getName(), product.getPrice(),
                    inventory.getQuantity(), product.getCategoryId()));
        }

        return res;
    }

    public ProductSummaryDto getSummaryById(Long id) {
        Product product = getById(id);
        Inventory inventory = inventoryService.getById(product.getId());
        Category category = categoryService.getById(product.getCategoryId());
        return new ProductSummaryDto(product.getId(), product.getName(), category.getName(), product.getPrice(),
                inventory.getQuantity(), product.getCategoryId());
    }


    public void deleteById(Long id) {
        Product product = getById(id);
        productRepository.deleteById(id);
        inventoryService.deleteById(product.getCategoryId());
    }

    public Product update(Long id, Product product, int stock) {
        if (!categoryService.existsById(product.getCategoryId())) {
            throw new IllegalArgumentException("No existe una categoría con la id = " + product.getCategoryId());
        }
        Product existing = getById(id);
        inventoryService.updateByProductId(id, stock);
//TODO validaciones
        existing.setName(product.getName());
        existing.setCategoryId(product.getCategoryId());
        existing.setPrice(product.getPrice());

        return productRepository.save(existing);
    }

    public List<ProductSummaryDto> search(String searchText) {
        String searchLower = searchText.toLowerCase();

        List<Long> matchingCategoryIds = categoryService.findIdsByName(searchText);
        List<Product> matches = productRepository.findAll().stream()
                .filter(p -> {
                    boolean nameMatches = p.getName().toLowerCase().contains(searchLower);
                    boolean categoryMatches = matchingCategoryIds.contains(p.getCategoryId());
                    return nameMatches || categoryMatches;
                })
                .toList();

        return matches.stream().map(this::convertToDto).toList();
    }

    private ProductSummaryDto convertToDto(Product p) {
        //prácticamente es como un mapper
        String catName = categoryService.getById(p.getCategoryId()).getName();
        int stock = inventoryService.getByProductId(p.getId()).getQuantity();
        return new ProductSummaryDto(
                p.getId(),
                p.getName(),
                catName,
                p.getPrice(),
                stock,
                p.getCategoryId()
        );
    }
}
