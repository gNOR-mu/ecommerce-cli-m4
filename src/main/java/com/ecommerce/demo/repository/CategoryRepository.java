package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.Category;

import java.util.List;

/**
 * Define el contrato de acceso a datos para la entidad {@link Category}.
 * <p>
 * Extiende las operaciones CRUD básicas y añade consultas específicas
 * relacionadas con el dominio de los productos.
 * </p>
 *
 * @author Gabriel Norambuena
 * @version 1.0
 * @see CrudRepository
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    /**
     * Obtiene las ids de las categorías cuyo texto coincida con el nombre
     * @param searchText Texto a buscar
     * @return Un listado con las ids coincidentes.
     */
    List<Long> findIdsByName(String searchText);

    /**
     * Verifica si existe una categoría con ese nombre
     * @param name Nombre a buscar
     * @return Verdadero si ya existe, falso en caso contrario
     */
    boolean existsByName(String name);
}
