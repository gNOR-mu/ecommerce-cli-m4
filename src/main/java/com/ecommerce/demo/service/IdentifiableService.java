package com.ecommerce.demo.service;

/**
 * Interfaz para la identificación de los servicios, contiene operaciones comunes para la identificación.
 * @param <T> Tipo de la clase
 * @param <ID> Tipo de identificación
 */
public interface IdentifiableService<T, ID> {
    /**
     * Verifica que el recurso no exista en la base de datos.
     * @param id Identificación del recurso a verificar.
     * @return Verdadero cuando no existe, falso en caso contrario.
     */
    boolean notExistsById(ID id);

    /**
     * Obtiene una entidad por su identificación
     * @param id Identificador del recurso
     * @return La entidad encontrada
     * @throws com.ecommerce.demo.exceptions.ResourceNotFoundException Si no existe un recurso con esa id
     */
    T getById(ID id);
}
