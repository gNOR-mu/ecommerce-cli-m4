package com.ecommerce.demo.exceptions;

public class InventoryException extends RuntimeException{
    public InventoryException(String msg){
        super(msg);
    }
}
