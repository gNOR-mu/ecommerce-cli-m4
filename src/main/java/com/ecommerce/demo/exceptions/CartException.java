package com.ecommerce.demo.exceptions;

public class CartException extends RuntimeException{
    public CartException(String msg){
        super(msg);
    }
}
