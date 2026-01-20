package com.ecommerce.demo.repository;

import java.util.List;
import java.util.Optional;

/**
 * CRUD simulado de un repositorio
 * @param <T> Tipo de la clase
 * @param <ID> Tipo de identificador de la clase
 */
public interface CrudRepository<T, ID> {
    /**
     * Guarda o actualiza una entidad en la base de datos
     * @param entity Entidad a almacenar
     * @return Entidad después de guardarla en la base de datos
     */
    T save(T entity);

    /**
     * Elimina una entidad basada en su Identificación
     * @param id Identificación de la entidad a eliminar
     */
    void deleteById(ID id);

    /**
     * Comprueba que exista en la base de datos
     * @param id Identificación a comprobar
     * @return verdadero si existe, falso en caso contrario
     */
    boolean existsById(ID id);

    /**
     * Busca una entidad en la base de datos
     * @param id Identificación de la entidad a buscar
     * @return Un optional con el entidad.
     */
    Optional<T> findById(ID id);

    /**
     * Busca todas las entidades en la base de datos.
     * @return Una lista con todas las entidades en la base de datos.
     */
    List<T> findAll();
}
