package com.ecommerce.demo.view.menu;

import com.ecommerce.demo.config.DependencyContainer;
import com.ecommerce.demo.util.PrintUtil;
import com.ecommerce.demo.view.menu.admin.AdminMenu;
import com.ecommerce.demo.view.menu.user.UserMenu;

/**
 * Menú principal
 */
public class MainMenu extends AbstractMenu {
    private final Menu userMenu;
    private final Menu adminMenu;

    /**
     * Constructor de la clase
     * @param dependencyContainer Contenedor con las dependencias
     *
     * @apiNote Tanto el adminMenu como userMenu son creados con el MainMenu, esto quiere decir que al salir de un usuario
     * o un administrador los datos no se perderán (como por ejemplo: un carrito)
     */
    public MainMenu(DependencyContainer dependencyContainer) {
        super(dependencyContainer.getInputHandler());

        this.adminMenu = new AdminMenu(
                dependencyContainer.getInputHandler(),
                dependencyContainer.getProductService(),
                dependencyContainer.getCategoryService());

        this.userMenu = new UserMenu(
                dependencyContainer.getInputHandler(),
                dependencyContainer.getProductService(),
                dependencyContainer.getInventoryService(),
                dependencyContainer.getDiscountCalculatorService(),
                dependencyContainer.getCartService(),
                dependencyContainer.getCheckoutService()
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
