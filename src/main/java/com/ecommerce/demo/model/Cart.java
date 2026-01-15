package com.ecommerce.demo.model;

import java.util.*;

public class Cart {
    private Map<Long, CartItem> items = new HashMap<>();

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

    public void clear() {
        items.clear();
    }

    public Optional<CartItem> getCartItem(Long productID){
        return Optional.ofNullable(items.get(productID));
    }

    public Optional<CartItem> removeItem(Long id){
        return Optional.ofNullable(items.remove(id));
    }

    public List<CartItem> getAll(){
        return new ArrayList<>(items.values());
    }
}
