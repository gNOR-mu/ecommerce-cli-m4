package com.ecommerce.demo.model;


import com.ecommerce.demo.model.base.Identifiable;

public class Product implements Identifiable<Long> {
    private Long id;
    private Long categoryId;

    private int price;
    private String name;

    public Product(Long id, Long categoryId, int price, String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.price = price;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
