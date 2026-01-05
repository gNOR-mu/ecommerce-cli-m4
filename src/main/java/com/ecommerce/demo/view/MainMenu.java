package com.ecommerce.demo.view;

import com.ecommerce.demo.DependencyContainer;
import com.ecommerce.demo.view.admin.ProductManagerMenu;
import com.ecommerce.demo.view.user.UserMenu;

public class MainMenu extends AbstractMenu {
    private final DependencyContainer dependencyContainer;

    public MainMenu(DependencyContainer dependencyContainer) {
        super(dependencyContainer.getINPUT_HANDLER());
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
                    dependencyContainer.getINPUT_HANDLER(),
                    dependencyContainer.getProductService(),
                    dependencyContainer.getCategoryService()));
            //TODO menu usuario
            case 2 -> MenuRunner.run((new UserMenu(dependencyContainer.getINPUT_HANDLER())));
            default -> System.out.println("Opción inválida");
        }
        return true;
    }
}
