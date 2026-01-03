package com.ecommerce.demo.view;

import java.util.Scanner;

public class UserMenu extends AbstractMenu {
    //TODO Definir servicios a utilizar

    public UserMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void printMenuOptions() {
        System.out.println("""
                **********************
                *** Menú USER ***
                **********************
                
                0) SALIR
                1) -
                2) -
                """);
    }

    @Override
    protected boolean handleOption(int option) {
        switch (option) {
            case 0 -> {
                return false;
            }
            default -> System.out.println("Opción inválida");
        }
        return true;
    }
}
