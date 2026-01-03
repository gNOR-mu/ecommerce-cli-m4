package com.ecommerce.demo.model;


import com.ecommerce.demo.model.base.Identifiable;

import java.math.BigDecimal;

public class Product implements Identifiable<Long> {
    private Long id;
    private Long categoryId;

    private BigDecimal price;
    private String name;

    public Product(Long categoryId, BigDecimal price, String name) {
        this.categoryId = categoryId;
        this.price = price;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
