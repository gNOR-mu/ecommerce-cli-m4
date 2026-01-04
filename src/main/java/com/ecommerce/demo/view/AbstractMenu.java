package com.ecommerce.demo.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractMenu implements Menu {
    private static final List<String> CONFIRMATION  = List.of("sí","si","s");

    protected final Scanner scanner;

    public AbstractMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printMenuOptions();
            int option = readInt("Ingrese una opción: ");
            running = handleOption(option);
        }
    }

    protected abstract void printMenuOptions();

    protected abstract boolean handleOption(int option);


    // métodos de utilidad para leer sobre el teclado
    protected String readText(String msg) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: El texto no puede estar vacío.");
        }
    }

    protected int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número entero válido.");
            }
        }
    }

    protected Long readLong(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número entero válido.");
            }
        }
    }

    protected BigDecimal readBigDecimal(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                String input = scanner.nextLine();
                BigDecimal value = new BigDecimal(input);

                // Validación extra: Que no sea negativo
                if (value.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Error: El precio no puede ser negativo.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un precio válido.");
            }
        }
    }

    protected boolean confirm(String msg){
        return CONFIRMATION.contains(msg);
    }
}
