package com.ecommerce.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg){
        super(msg);
    }

    public ResourceNotFoundException(String resource, Long id) {
        super("%s no encontrado con id : %d".formatted(resource, id));
    }
}
