package com.ecommerce.demo;

import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.CategoryService;
import com.ecommerce.demo.service.ProductService;

import java.math.BigDecimal;

/**
 * @param PRODUCT_SERVICE servicio de productos
 * @param CATEGORY_SERVICE servicio de categorías
 */
public record DataSeeder(ProductService PRODUCT_SERVICE, CategoryService CATEGORY_SERVICE) {

    public void loadData() {
        // categorías iniciales
        CATEGORY_SERVICE.create(new Category("Accesorio"));
        CATEGORY_SERVICE.create(new Category("Expedición"));
        CATEGORY_SERVICE.create(new Category("Vestimenta"));
        // productos iniciales
        PRODUCT_SERVICE.create(new Product(1L, new BigDecimal("25000"), "Lentes de sol"), 12);
        PRODUCT_SERVICE.create(new Product(1L, new BigDecimal("100000"), "Mochila"), 32);
        PRODUCT_SERVICE.create(new Product(2L, new BigDecimal("55000"), "Carpa"), 14);
        PRODUCT_SERVICE.create(new Product(3L, new BigDecimal("50000"), "Polera térmica"), 25);

    }
}
