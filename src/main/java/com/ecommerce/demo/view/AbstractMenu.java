package com.ecommerce.demo.view;

import com.ecommerce.demo.util.InputHandler;

import static com.ecommerce.demo.util.Constants.CONFIRMATION;

public abstract class AbstractMenu implements Menu {
    protected final InputHandler inputHandler;

    protected AbstractMenu(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public void show() {
        boolean running = true;
        while (running) {
            //imprimo el menú de la clase que lo implementa
            printMenuOptions();

            //pido que ingrese una opción
            int option = inputHandler.readInt("Ingrese una opción: ");

            // basado en la opción que ingreso, la implementación de handleOPtion decide si salir.
            running = handleOption(option);
        }
    }

    protected abstract void printMenuOptions();

    protected abstract boolean handleOption(int option);

    protected boolean confirm(String msg) {
        return CONFIRMATION.contains(msg.toLowerCase());
    }
}
