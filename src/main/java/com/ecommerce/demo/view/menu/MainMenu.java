package com.ecommerce.demo.view.menu;

import com.ecommerce.demo.config.DependencyContainer;
import com.ecommerce.demo.enums.MainMenuOptions;
import com.ecommerce.demo.util.MenuOption;
import com.ecommerce.demo.util.PrintUtil;
import com.ecommerce.demo.view.menu.admin.AdminMenu;
import com.ecommerce.demo.view.menu.user.UserMenu;

import java.util.Optional;

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
    protected boolean handleOption(int code) {
        MainMenuOptions option = MenuOption.find(MainMenuOptions.class, code).orElse(null);

        switch (option) {
            case ADMIN -> MenuRunner.run(adminMenu);
            case USER -> MenuRunner.run(userMenu);
            case EXIT -> {
                return false;
            }
            case null, default -> System.out.println("Opción inválida");
        }
        return true;
    }
}
