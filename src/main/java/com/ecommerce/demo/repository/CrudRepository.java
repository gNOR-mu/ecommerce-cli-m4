package com.ecommerce.demo.repository;

import java.util.Optional;

/*
 * CRUD de un repositorio simulado
 * */
public interface CrudRepository<T, ID> {
    T save(T entity);
    void deleteById(ID id);
    boolean existsById(ID id);
    Optional<T> findById(ID id);
}
