package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.Order;

/**
 * Define el contrato de acceso a datos para la entidad {@link Order}.
 * <p>
 * Extiende las operaciones CRUD básicas y añade consultas específicas
 * relacionadas con el dominio de los productos.
 * </p>
 *
 * @author Gabriel Norambuena
 * @version 1.0
 * @see CrudRepository
 */
public interface OrderRepository extends CrudRepository<Order,Long> {
}
