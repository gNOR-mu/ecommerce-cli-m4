package com.ecommerce.demo.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public abstract class AbstractMenu implements Menu {
    private static final List<String> CONFIRMATION = List.of("sí", "si");

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
    private <T> T readInput(String msg, Function<String, T> parser) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim();
            try {
                return parser.apply(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected String readText(String msg) {
        return readInput(msg, str -> {
            if (str.isEmpty()) throw new IllegalArgumentException();
            return str;
        });
    }

    protected int readInt(String msg) {
        return readInput(msg, Integer::parseInt);
    }

    protected Long readLong(String msg) {
        return readInput(msg, Long::parseLong);
    }

    protected BigDecimal readBigDecimal(String msg) {
        return readInput(msg, str -> {
            BigDecimal val = new BigDecimal(str);
            if (val.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("El número no debe ser negativo");
            return val;
        });
    }

    protected boolean confirm(String msg) {
        return CONFIRMATION.contains(msg.toLowerCase());
    }
}
