package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.base.Identifiable;
import com.ecommerce.demo.repository.CrudRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public abstract class InMemoryAbstractRepository<T extends Identifiable<Long>> implements CrudRepository<T, Long> {
    //simulación de la base de datos
    protected final Map<Long, T> DB = new HashMap<>();

    // simulación del incrementado de índice automático al crear un registro en la base de datos
    private final AtomicLong ID_GENERATOR = new AtomicLong(0);

    @Override
    public T save(T entity) {
        if(entity.getId() == null){
            // no existe el registro, por lo tanto, debo asignarle una nueva id
            long newId = ID_GENERATOR.incrementAndGet();
            entity.setId(newId);
            DB.put(entity.getId(), entity);
        }else{
            // si ya existe solo actualizo el mapa
            DB.put(entity.getId(), entity);
        }
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        DB.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return DB.containsKey(id);
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(DB.get(id));
    }

    @Override
    public List<T> findAll(){
        return new ArrayList<>(DB.values());
    }
}
