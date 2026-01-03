package com.ecommerce.demo.model;

import com.ecommerce.demo.model.base.Identifiable;

public class Category implements Identifiable<Long> {
    private Long id;

    private String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
