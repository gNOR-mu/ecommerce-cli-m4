package com.ecommerce.demo.util;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.function.Function;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

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

    public String readText(String msg) {
        return readInput(msg, str -> {
            if (str.isEmpty()) throw new IllegalArgumentException();
            return str;
        });
    }

    public int readInt(String msg) {
        return readInput(msg, Integer::parseInt);
    }

    public Long readLong(String msg) {
        return readInput(msg, Long::parseLong);
    }

    public BigDecimal readBigDecimal(String msg) {
        return readInput(msg, str -> {
            BigDecimal val = new BigDecimal(str);
            if (val.compareTo(BigDecimal.ZERO) < 0)
                throw new IllegalArgumentException("El número no debe ser negativo");
            return val;
        });
    }
}
