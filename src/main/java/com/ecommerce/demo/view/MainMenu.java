package com.ecommerce.demo.view;

import com.ecommerce.demo.DependencyContainer;
import com.ecommerce.demo.util.PrintUtil;
import com.ecommerce.demo.view.admin.AdminMenu;
import com.ecommerce.demo.view.user.UserMenu;

/**
 * Menú principal
 */
public class MainMenu extends AbstractMenu {
    /**
     * Nota: Constructor con las dependencias, en caso de que un menú necesite utilizar alguna dependencia en específico
     * se le pasa solo la dependencia que necesita y no el contenedor completo
     */
    private final DependencyContainer DEPENDENCY_CONTAINER;

    private Menu userMenu;
    private Menu adminMenu;

    /**
     * Constructor de la clase
     * @param dependencyContainer Contenedor con las dependencias
     *
     * @apiNote Tanto el adminMenu como userMenu son creados con el MainMenu, esto quiere decir que al salir de un usuario
     * o un administrador los datos no se perderán (como por ejemplo: un carrito)
     */
    public MainMenu(DependencyContainer dependencyContainer) {
        super(dependencyContainer.getInputHandler());
        this.DEPENDENCY_CONTAINER = dependencyContainer;

        this.adminMenu = new AdminMenu(
                DEPENDENCY_CONTAINER.getInputHandler(),
                DEPENDENCY_CONTAINER.getProductService(),
                DEPENDENCY_CONTAINER.getCategoryService());

        this.userMenu = new UserMenu(
                DEPENDENCY_CONTAINER.getInputHandler(),
                DEPENDENCY_CONTAINER.getProductService(),
                DEPENDENCY_CONTAINER.getInventoryService(),
                DEPENDENCY_CONTAINER.getDiscountCalculatorService()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void printMenuOptions() {
        PrintUtil.printMainMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean handleOption(int option) {
        switch (option) {
            case 0 -> {
                return false;
            }
            case 1 -> MenuRunner.run(adminMenu);
            case 2 -> MenuRunner.run(userMenu);
            default -> System.out.println("Opción inválida");
        }
        return true;
    }
}
