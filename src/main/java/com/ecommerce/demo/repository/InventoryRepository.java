package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.Inventory;

import java.util.Optional;

/**
 * Define el contrato de acceso a datos para la entidad {@link Inventory}.
 * <p>
 * Extiende las operaciones CRUD básicas y añade consultas específicas
 * relacionadas con el dominio de los productos.
 * </p>
 *
 * @author Gabriel Norambuena
 * @version 1.0
 * @see CrudRepository
 */
public interface InventoryRepository extends CrudRepository<Inventory,Long> {
    /**
     * Busca un inventario basado en la identificación de un producto.
     * @param productId Identificación del producto
     * @return Un Optional con el inventario.
     */
    Optional<Inventory> findByProductId(Long productId);
}
