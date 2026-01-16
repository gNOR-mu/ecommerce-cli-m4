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

    public void loadData() {
        // categorías iniciales
        CATEGORY_SERVICE.create(new Category("Accesorio"));     //1
        CATEGORY_SERVICE.create(new Category("Expedición"));    //2
        CATEGORY_SERVICE.create(new Category("Vestimenta"));    //3
        CATEGORY_SERVICE.create(new Category("Transporte"));   //4
        // productos iniciales
        PRODUCT_SERVICE.create(new Product(1L, new BigDecimal("25000"), "Lentes de sol"), 85);
        PRODUCT_SERVICE.create(new Product(2L, new BigDecimal("55000"), "Carpa"), 34);
        PRODUCT_SERVICE.create(new Product(3L, new BigDecimal("50000"), "Polera térmica"), 25);
        PRODUCT_SERVICE.create(new Product(4L, new BigDecimal("200000"), "Bicicleta"), 32);

    }
}
