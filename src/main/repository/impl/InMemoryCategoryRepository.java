package main.repository.impl;

import main.model.Category;
import main.repository.CategoryRepository;

public class InMemoryCategoryRepository extends InMemoryAbstractRepository<Category, Long> implements CategoryRepository {
}
