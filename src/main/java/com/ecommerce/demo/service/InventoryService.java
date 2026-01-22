package com.ecommerce.demo.service;

import com.ecommerce.demo.exceptions.InventoryException;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.repository.InventoryRepository;

/**
 * Servicio para la gestión de inventario
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class InventoryService implements IdentifiableService<Inventory, Long> {
    private final InventoryRepository inventoryRepository;

    /**
     * Constructor de la clase
     * @param inventoryRepository Repositorio del inventario.
     */
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean notExistsById(Long id) {
        return !inventoryRepository.existsById(id);
    }

    /**
     * Crea un nuevo inventario.
     * @param inventory Inventario a crear.
     * @return Nuevo inventario.
     *
     * @throws IllegalArgumentException Cuando el inventario es inferior a 0 (quantity < 0)
     * @throws InventoryException Cuando ya existe un inventario para un producto.
     */
    public Inventory create(Inventory inventory) {
        if (inventory.getQuantity() < 0) {
            throw new IllegalArgumentException("El inventario no puede ser inferior a 0");
        }
        if(inventoryRepository.existsById(inventory.getProductId())){
            throw new InventoryException("Ya existe un inventario para el producto con id : " + inventory.getProductId());
        }
        return inventoryRepository.save(inventory);
    }

    /**
     * Elimina el inventario de un producto.
     * @param productId Identificación del producto
     *
     * @throws ResourceNotFoundException Cuando no se encuentra un inventario (viene de getById).
     * @throws InventoryException Cuando el inventario aún presenta productos. (quantity > 0).
     *
     */
    public void deleteByProductId(Long productId) {
        Inventory inventory = getByProductId(productId);

        if(inventory.getQuantity() > 0){
            throw new InventoryException("No se puede eliminar un inventario con cantidad de productos > 0");
        }
        inventoryRepository.deleteById(inventory.getId());
    }

    /**
     * Actualiza la cantidad de inventario correspondiente.
     * @param productId Identificación del producto.
     * @param quantity Nueva cantidad del producto.
     * @return Entidad actualizada.
     *
     * @throws IllegalArgumentException Cuando el inventario es inferior a 0
     */
    public Inventory updateByProductId(Long productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("El inventario no puede ser inferior a 0");
        }
        Inventory existing = getByProductId(productId);
        existing.setQuantity(quantity);
        return inventoryRepository.save(existing);
    }

    /**
     * Obtiene el inventario de un producto determinado.
     * @param productId Identificatión del producto al cual le pertenece el inventario
     * @return Inventario del producto.
     */
    public Inventory getByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId).orElseThrow(
                () -> new ResourceNotFoundException("No se ha encontrado un inventario correspondiente a un producto con id : "+productId));
    }

    /**
     * Reduce el inventario de un producto
     * @param productId Identificación del producto
     * @param quantity Cantidad a reducir
     *
     * @throws IllegalArgumentException Cuando se intenta reducir más productos del stock disponible
     */
    public void subtractInventory(Long productId, int quantity){
        Inventory inventory = getByProductId(productId);
        int newQuantity = inventory.getQuantity() - quantity;
        if(newQuantity < 0){
            throw  new IllegalArgumentException("No puede haber un stock negativo");
        }
        inventory.setQuantity(newQuantity);
        inventoryRepository.save(inventory);
    }

    /**
     * Comprueba que sea posible reducir el inventario de un producto determinado.
     * @apiNote Se considera que se puede reducir cuando el inventario resultante es >= 0
     *
     * @param productId Identificación del producto
     * @param quantity Cantidad a reducir
     * @return Verdadero si se puede reducir el inventario, falso en caso contrario
     */
    public boolean isPossibleSubtract(Long productId, int quantity){
        Inventory inventory = getByProductId(productId);
        return inventory.getQuantity() - quantity >= 0;
    }

}
