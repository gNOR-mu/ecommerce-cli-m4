package com.ecommerce.demo.view;

import com.ecommerce.demo.DependencyContainer;
import com.ecommerce.demo.view.admin.ProductManagerMenu;
import com.ecommerce.demo.view.user.UserMenu;

import java.util.Scanner;

public class MainMenu extends AbstractMenu {
    private final DependencyContainer dependencyContainer;

    public MainMenu(DependencyContainer dependencyContainer) {
        super(dependencyContainer.getInputHandler());
        this.dependencyContainer = dependencyContainer;
    }

    @Override
    protected void printMenuOptions() {
        System.out.println("""
                +-----------------------------------+
                |   Menú principal                  |
                +-----------------------------------+
                |   1. Administrador                |
                |   2. Usuario                      |
                |   0. Salir                        |
                +-----------------------------------+
                """);
    }

    @Override
    protected boolean handleOption(int option) {
        switch (option) {
            case 0 -> {
                return false;
            }
            // por el momento es innecesario tener una clase intermedia como admin debido a los requerimientos
            // así que ingreso directamente a la administración de productos
            case 1 -> MenuRunner.run(new ProductManagerMenu(
                    dependencyContainer.getInputHandler(),
                    dependencyContainer.getProductService(),
                    dependencyContainer.getCategoryService()));
            //TODO menu usuario
            case 2 -> MenuRunner.run((new UserMenu(dependencyContainer.getInputHandler())));
            default -> System.out.println("Opción inválida");
        }
        return true;
    }
}
