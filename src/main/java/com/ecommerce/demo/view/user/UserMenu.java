package com.ecommerce.demo.view.user;

import com.ecommerce.demo.util.InputHandler;
import com.ecommerce.demo.view.AbstractMenu;

import java.util.Scanner;

public class UserMenu extends AbstractMenu {
    //TODO Definir servicios a utilizar

    public UserMenu(InputHandler inputHandler) {
        super(inputHandler);
    }

    @Override
    protected void printMenuOptions() {
        System.out.println("""
                +-----------------------------------+
                |   Usuario                         |
                +-----------------------------------+
                |   1) Listar productos             |
                |   2) -                            |
                |   3) -                            |
                |   4) -                            |
                |   5) -                            |
                |   0) Salir                        |
                +-----------------------------------+
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
