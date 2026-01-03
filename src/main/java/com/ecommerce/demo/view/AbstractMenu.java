package com.ecommerce.demo.view;

import java.math.BigDecimal;
import java.util.Scanner;

public abstract class AbstractMenu implements Menu {
    protected final Scanner scanner;

    public AbstractMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printMenuOptions();
            try {
                System.out.print("Ingrese una opción: ");
                int option = Integer.parseInt(scanner.nextLine());
                running = handleOption(option);

                //TODO catch de posibles errores como ingreso de letras, etc
            } catch (Exception e) {
                System.out.println("Algo ha salido mal");
                e.printStackTrace();
            }
        }
    }

    protected abstract void printMenuOptions();

    protected abstract boolean handleOption(int option);


    // métodos de utilidad para leer sobre el teclado
    protected String readText(String msg){
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: El texto no puede estar vacío.");
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
                BigDecimal valor = new BigDecimal(input);

                // Validación extra: Que no sea negativo
                if (valor.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Error: El precio no puede ser negativo.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un precio válido.");
            }
        }
    }
}
