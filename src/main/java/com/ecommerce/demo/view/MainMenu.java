package com.ecommerce.demo.view;

import com.ecommerce.demo.DependencyContainer;

import java.util.Scanner;

public class MainMenu extends AbstractMenu {
    private final DependencyContainer dependencyContainer;

    public MainMenu(DependencyContainer dependencyContainer, Scanner scanner) {
        super(scanner);
        this.dependencyContainer = dependencyContainer;
    }

    @Override
    protected void printMenuOptions() {
        System.out.println("""
                **********************
                *** Menú principal ***
                **********************
                
                0) SALIR
                1) ADMIN
                2) USUARIO
                """);
    }

    @Override
    protected boolean handleOption(int option) {
        switch (option) {
            case 0 -> {
                return false;
            }
            case 1 -> MenuRunner.run(new AdminMenu(scanner));
            default -> System.out.println("Opción inválida");
        }
        return true;
    }
}
