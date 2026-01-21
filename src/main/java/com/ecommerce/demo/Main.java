package com.ecommerce.demo;


import com.ecommerce.demo.config.DependencyContainer;
import com.ecommerce.demo.view.MainMenu;
import com.ecommerce.demo.view.Menu;

/**
 * Clase principal
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class Main {

    /**
     * @param args No se utilizan argumentos
     */
    public static void main(String[] args) {
        DependencyContainer dependencyContainer = new DependencyContainer();
        Menu mainMenu = new MainMenu(dependencyContainer);
        mainMenu.show();
    }
}
