package com.ecommerce.demo;

import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.CategoryService;
import com.ecommerce.demo.service.ProductService;

import java.math.BigDecimal;

public class DataSeeder {
    //poblado de datos iniciales
    private final ProductService productService;
    private final CategoryService categoryService;

    public DataSeeder(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    public void loadData() {
        // categorías iniciales
        categoryService.create(new Category("Accesorio"));
        categoryService.create(new Category("Expedición"));
        categoryService.create(new Category("Vestimenta"));
        // productos iniciales
        productService.create(new Product(1L, new BigDecimal("25000"), "Lentes de sol"));
        productService.create(new Product(1L, new BigDecimal("100000"), "Mochila"));
        productService.create(new Product(2L, new BigDecimal("55000"), "Carpa"));
        productService.create(new Product(3L, new BigDecimal("50000"), "Polera térmica"));

    }
}
