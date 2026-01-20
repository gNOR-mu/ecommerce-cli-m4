package com.ecommerce.demo;

import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.CategoryService;
import com.ecommerce.demo.service.ProductService;

import java.math.BigDecimal;

/**
 * @param PRODUCT_SERVICE  servicio de productos
 * @param CATEGORY_SERVICE servicio de categorías
 */
public record DataSeeder(ProductService PRODUCT_SERVICE, CategoryService CATEGORY_SERVICE) {

    /**
     * Inicializa los datos
     */
    public void loadData() {
        // categorías iniciales
        Category c1 = CATEGORY_SERVICE.create(new Category("Accesorio"));
        Category c2 = CATEGORY_SERVICE.create(new Category("Expedición"));
        Category c3 = CATEGORY_SERVICE.create(new Category("Vestimenta"));
        Category c4 = CATEGORY_SERVICE.create(new Category("Transporte"));
        // productos iniciales
        PRODUCT_SERVICE.create(new Product(c1.getId(), new BigDecimal("25000"), "Lentes de sol"), 85);
        PRODUCT_SERVICE.create(new Product(c2.getId(), new BigDecimal("55000"), "Carpa"), 34);
        PRODUCT_SERVICE.create(new Product(c3.getId(), new BigDecimal("50000"), "Polera térmica"), 25);
        PRODUCT_SERVICE.create(new Product(c4.getId(), new BigDecimal("200000"), "Bicicleta"), 32);

    }
}
