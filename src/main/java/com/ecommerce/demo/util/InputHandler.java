package com.ecommerce.demo.util;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.function.Function;

public class InputHandler {
    private final Scanner SCANNER;

    public InputHandler(Scanner scanner) {
        this.SCANNER = scanner;
    }

    private <T> T readInput(String msg, Function<String, T> parser) {
        while (true) {
            System.out.print(msg);
            String input = SCANNER.nextLine().trim();
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
        return readInput(msg, str -> {
            try {
                int val = Integer.parseInt(str);
                if (val < 0) {
                    throw new IllegalArgumentException("Error: El número no debe ser negativo.");
                }
                return val;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Debes ingresar un número entero válido.");
            }
        });
    }

    public Long readLong(String msg) {
        return readInput(msg, str -> {
            try {
                long val = Long.parseLong(str);
                if (val < 0) {
                    throw new IllegalArgumentException("Error: El número no debe ser negativo.");
                }
                return val;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Debes ingresar un número entero válido.");
            }
        });
    }

    public BigDecimal readBigDecimal(String msg) {
        return readInput(msg, str -> {
            try {
                BigDecimal val = new BigDecimal(str);
                if (val.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("Error: El número no debe ser negativo.");
                }

                return val;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Debes ingresar un número decimal válido.");
            }
        });
    }

    public String editString(String msg, String actualValue) {
        System.out.printf("%s [%s]: ", msg, actualValue);

        String input = SCANNER.nextLine().trim();

        // Si está vacío, devuelve el valor viejo. Si no, el nuevo.
        if (input.isEmpty()) {
            return actualValue;
        }
        return input;
    }

    public long editLong(String mensaje, Long valorActual) {
        while (true) {
            System.out.printf("%s [%d]: ", mensaje, valorActual);
            String input = SCANNER.nextLine().trim();

            if (input.isEmpty()) {
                return valorActual;
            }

            try {
                return Long.parseLong(input);

            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número entero válido.");
            }
        }
    }

    public int editInt(String mensaje, int valorActual) {
        while (true) {
            System.out.printf("%s [%d]: ", mensaje, valorActual);
            String input = SCANNER.nextLine().trim();

            if (input.isEmpty()) {
                return valorActual;
            }

            try {
                int nuevoValor = Integer.parseInt(input);
                if (nuevoValor < 0) {
                    System.out.println("El valor no puede ser negativo.");
                    continue;
                }

                return nuevoValor;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número entero válido.");
            }
        }
    }

    public BigDecimal editBigDecimal(String mensaje, BigDecimal valorActual) {
        while (true) {
            System.out.printf("%s [%s]: ", mensaje, valorActual);
            String input = SCANNER.nextLine().trim();

            if (input.isEmpty()) {
                return valorActual;
            }

            try {
                BigDecimal nuevoValor = new BigDecimal(input);
                if (nuevoValor.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("El número no debe ser negativo");
                    continue;
                }
                return nuevoValor;
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido o presiona Enter.");
            }
        }
    }
}
