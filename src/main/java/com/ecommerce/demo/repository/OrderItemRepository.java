package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.OrderItem;

/**
 * Define el contrato de acceso a datos para la entidad {@link OrderItem}.
 * <p>
 * Extiende las operaciones CRUD básicas y añade consultas específicas
 * relacionadas con el dominio de los productos.
 * </p>
 *
 * @author Gabriel Norambuena
 * @version 1.0
 * @see CrudRepository
 */
public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
}
