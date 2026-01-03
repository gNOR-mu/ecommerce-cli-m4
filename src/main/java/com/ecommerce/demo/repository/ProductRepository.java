package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.Product;

import java.util.List;

/**
 * Define el contrato de acceso a datos para la entidad {@link Product}.
 * <p>
 * Extiende las operaciones CRUD básicas y añade consultas específicas
 * relacionadas con el dominio de los productos.
 * </p>
 *
 * @author Gabriel Norambuena
 * @version 1.0
 * @see CrudRepository
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    /**
     * Recupera una lista de productos cuyo nombre contiene el texto proporcionado ignorando el caso.
     * <p>
     * La búsqueda no debe distinguir entre mayúsculas y minúsculas (case-insensitive).
     * Si el término de búsqueda es vacío, el comportamiento depende de la implementación.
     * </p>
     *
     * @param name El texto parcial o completo a buscar en el nombre del producto.
     * No debe ser nulo.
     * @return Una lista inmutable de productos que coinciden con el criterio.
     * Devuelve una lista vacía si no se encuentran coincidencias.
     * Nunca devuelve {@code null}.
     */
    List<Product> findByNameIgnoreCase(String name);

    /**
     * Recupera una lista de productos cuya id de la categoría coincide con la id de categoría proporcionada.
     * <p>
     * La búsqueda debe ser exacta con la id de categoría.
     * Si el término de búsqueda es vacío, el comportamiento depende de la implementación.
     * </p>
     *
     * @param categoryId id de la categoría a buscar.
     * No debe ser nulo.
     * @return Una lista inmutable de productos que coinciden con el criterio.
     * Devuelve una lista vacía si no se encuentran coincidencias.
     * Nunca devuelve {@code null}.
     */
    List<Product> findByCategoryId(Long categoryId);
}
