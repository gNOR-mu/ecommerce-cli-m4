package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.base.Identifiable;
import com.ecommerce.demo.repository.CrudRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación en memoria del repositorio {@link CrudRepository}
*  <p>
 * Utiliza un {@link java.util.HashMap} para simular la persistencia de datos.
 * Esta implementación no es persistente: los datos se perderán al reiniciar la aplicación.
 * </p>
 * @param <T> Tipo de la clase
 *
 * @author Gabriel Norambuena
 * @version 1.0
 */
public abstract class InMemoryAbstractRepository<T extends Identifiable<Long>> implements CrudRepository<T, Long> {
    /**
     * Simulación de la base de datos en memoria con un HashMap
     */
    protected final Map<Long, T> DB = new HashMap<>();

    /**
     * Simulación del incrementado de índice automático al crear un registro en la base de datos
     */
    private final AtomicLong ID_GENERATOR = new AtomicLong(0);

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        DB.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsById(Long id) {
        return DB.containsKey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(DB.get(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(){
        return new ArrayList<>(DB.values());
    }
}
