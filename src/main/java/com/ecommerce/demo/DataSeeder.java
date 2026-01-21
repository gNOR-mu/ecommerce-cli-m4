package com.ecommerce.demo;

import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.CategoryService;
import com.ecommerce.demo.service.ProductService;

import java.math.BigDecimal;

/**
 * Inicializador de datos iniciales.
 * @param productService  servicio de productos
 * @param categoryService servicio de categorías
 * @author Gabriel Norambuena
 * @version 1.0
 */
public record DataSeeder(ProductService productService, CategoryService categoryService) {

    /**
     * Inicializa los datos
     */
    public void loadData() {
        // categorías iniciales
        Category c1 = categoryService.create(new Category("Accesorio"));
        Category c2 = categoryService.create(new Category("Expedición"));
        Category c3 = categoryService.create(new Category("Vestimenta"));
        Category c4 = categoryService.create(new Category("Transporte"));
        // productos iniciales
        productService.create(new Product(c1.getId(), new BigDecimal("25000"), "Lentes de sol"), 85);
        productService.create(new Product(c2.getId(), new BigDecimal("55000"), "Carpa"), 34);
        productService.create(new Product(c3.getId(), new BigDecimal("50000"), "Polera térmica"), 25);
        productService.create(new Product(c4.getId(), new BigDecimal("200000"), "Bicicleta"), 32);

    }
}
