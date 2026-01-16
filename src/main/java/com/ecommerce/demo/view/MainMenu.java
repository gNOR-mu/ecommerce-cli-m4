package com.ecommerce.demo.view;

import com.ecommerce.demo.DependencyContainer;
import com.ecommerce.demo.util.PrintUtil;
import com.ecommerce.demo.view.admin.AdminMenu;
import com.ecommerce.demo.view.user.UserMenu;

public class MainMenu extends AbstractMenu {
    private final DependencyContainer DEPENDENCY_CONTAINER;

    public MainMenu(DependencyContainer dependencyContainer) {
        super(dependencyContainer.getInputHandler());
        this.DEPENDENCY_CONTAINER = dependencyContainer;
    }

    @Override
    protected void printMenuOptions() {
        PrintUtil.printMainMenu();
    }

    @Override
    protected boolean handleOption(int option) {
        switch (option) {
            case 0 -> {
                return false;
            }
            // por el momento es innecesario tener una clase intermedia como admin debido a los requerimientos
            // así que ingreso directamente a la administración de productos
            case 1 -> MenuRunner.run(new AdminMenu(
                    DEPENDENCY_CONTAINER.getInputHandler(),
                    DEPENDENCY_CONTAINER.getProductService(),
                    DEPENDENCY_CONTAINER.getCategoryService()));

            case 2 -> MenuRunner.run((new UserMenu(
                    DEPENDENCY_CONTAINER.getInputHandler(),
                    DEPENDENCY_CONTAINER.getProductService(),
                    DEPENDENCY_CONTAINER.getInventoryService(),
                    DEPENDENCY_CONTAINER.getDiscountCalculatorService()
            )));
            default -> System.out.println("Opción inválida");
        }
        return true;
    }
}
