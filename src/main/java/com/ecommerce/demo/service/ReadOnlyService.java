package com.ecommerce.demo.service;

import java.util.Optional;

public interface ReadOnlyService <T,ID>{
    Optional<T> findById(ID id);
}
