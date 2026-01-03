package com.ecommerce.demo.enums;

public enum PaymentsMethod {
    CASH("Efectivo"),
    CARD("Tarjeta");

    private final String NAME;

    private PaymentsMethod(String name) {
        this.NAME = name;
    }

    public String getNAME() {
        return NAME;
    }
}
