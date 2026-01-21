package com.ecommerce.demo.model;

import java.math.BigDecimal;
import java.util.*;

/**
 * Carrito del ecommerce.
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class Cart {
    private final Map<Long, CartItem> items = new HashMap<>();

    /**
     * Añade un producto al carro.
     * Si el producto no existe lo crea.
     * Si existe, aumenta la cantidad del producto.
     * @param product Producto a añadir
     * @param quantity Cantidad del producto
     */
    public void addProduct(Product product, int quantity) {
        //añade un producto al carro

        if (items.containsKey(product.getId())) {
            //si existe le aumento la cantidad
            CartItem cartItem = items.get(product.getId());
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            //si no existe lo añado
            items.put(product.getId(), new CartItem(product, quantity));
        }
    }

    /**
     * Limpia el carro
     */
    public void clear() {
        items.clear();
    }

    /**
     * Obtiene un producto del carro.
     * @param productID Identificación del producto.
     * @return Un Optional correspondiente al producto.
     */
    public Optional<CartItem> getCartItem(Long productID) {
        return Optional.ofNullable(items.get(productID));
    }

    /**
     * Remueve un objeto del carro.
     * @param id Identificación del objeto a remover.
     * @return Un optional correspondiente al objeto eliminado.
     */
    public Optional<CartItem> removeItem(Long id) {
        return Optional.ofNullable(items.remove(id));
    }

    /**
     * Obtiene todos los items del carro
     * @return Una lista con todos los items del carro.
     */
    public List<CartItem> getAllItems() {
        return new ArrayList<>(items.values());
    }

    /**
     * Obtiene el total del carro sumando todos los subtotales
     * @apiNote No aplica descuentos
     * @return Total del carro
     */
    public BigDecimal getTotal() {
        return items.values().stream()
                .map(CartItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
