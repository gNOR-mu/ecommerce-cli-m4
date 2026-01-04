package com.ecommerce.demo.service;

import java.util.Optional;

public interface ReadOnlyService<T, ID> {
    Optional<T> findById(ID id);

    boolean existsById(ID id);

    default T getById(ID id) {
        return findById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado con id = " + id));
    }

}
