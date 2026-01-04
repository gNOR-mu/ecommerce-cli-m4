package com.ecommerce.demo.view;

import com.ecommerce.demo.util.InputHandler;

import java.util.List;
import java.util.Scanner;

public abstract class AbstractMenu implements Menu {
    private static final List<String> CONFIRMATION = List.of("sí", "si");
    protected final InputHandler inputHandler;
    protected final Scanner scanner;

    public AbstractMenu(Scanner scanner) {
        this.inputHandler = new InputHandler(scanner);
        this.scanner = scanner;
    }

    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printMenuOptions();
            int option = inputHandler.readInt("Ingrese una opción: ");
            running = handleOption(option);
        }
    }

    protected abstract void printMenuOptions();

    protected abstract boolean handleOption(int option);


    // métodos de utilidad para leer sobre el teclado


    protected boolean confirm(String msg) {
        return CONFIRMATION.contains(msg.toLowerCase());
    }
}
