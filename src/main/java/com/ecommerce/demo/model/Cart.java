package com.ecommerce.demo.model;

import java.math.BigDecimal;
import java.util.*;

public class Cart {
    private Map<Long, CartItem> items = new HashMap<>();
    private BigDecimal total = BigDecimal.ZERO;

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
        updateTotal();
    }

    public void clear() {
        items.clear();
        total = BigDecimal.ZERO;
    }

    public Optional<CartItem> getCartItem(Long productID) {
        return Optional.ofNullable(items.get(productID));
    }

    public Optional<CartItem> removeItem(Long id) {
        return Optional.ofNullable(items.remove(id));
    }

    public List<CartItem> getAllItems() {
        return new ArrayList<>(items.values());
    }

    private void updateTotal() {
        //si es un solo producto que se cambia no es óptimo calcular todos los items
        total = items.values().stream()
                .map(i -> i.getProduct().getPrice().multiply(new BigDecimal(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotal() {
        return total;
    }
}
