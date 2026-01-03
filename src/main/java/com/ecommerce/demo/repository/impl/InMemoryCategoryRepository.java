package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.repository.CategoryRepository;

public class InMemoryCategoryRepository extends InMemoryAbstractRepository<Category, Long> implements CategoryRepository {
}
