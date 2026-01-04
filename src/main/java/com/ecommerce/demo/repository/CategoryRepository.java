package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Long> findIdsByName(String searchText);
}
