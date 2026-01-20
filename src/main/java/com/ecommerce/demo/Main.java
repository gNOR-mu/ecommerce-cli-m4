package com.ecommerce.demo;


import com.ecommerce.demo.view.MainMenu;
import com.ecommerce.demo.view.Menu;

/**
 * Clase principal
 */
public class Main {

    public static void main(String[] args) {
        DependencyContainer dependencyContainer = new DependencyContainer();
        Menu mainMenu = new MainMenu(dependencyContainer);
        mainMenu.show();
    }
}
