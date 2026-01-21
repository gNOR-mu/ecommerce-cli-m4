package com.ecommerce.demo.util;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Clase de utilidad que manipula las entradas de teclado
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class InputHandler {
    private final Scanner SCANNER;

    /**
     * Constructor de la clase
     * @param scanner Scanner
     */
    public InputHandler(Scanner scanner) {
        this.SCANNER = scanner;
    }

    /**
     * Lee una entrada.
     * Aplica un parser para validar la entrada.
     * @param msg Mensaje a imprimir
     * @param parser Parser a aplicar
     * @return Entrada de teclado
     * @param <T> Tipo de dato
     */
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

    /**
     * Lee un texto por consola
     * @param msg Mensaje a mostrar por consola
     * @return Texto ingresado en consola
     */
    public String readText(String msg) {
        return readInput(msg, str -> {
            if (str.isEmpty()) throw new IllegalArgumentException();
            return str;
        });
    }

    /**
     * Lee un entero por consola
     * @param msg Mensaje a mostrar por consola
     * @return Entero ingresado en consola
     */
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

    /**
     * Lee un long por consola
     * @param msg Mensaje a mostrar por consola
     * @return Long ingresado en consola
     */
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

    /**
     * Lee un BigDecimal por consola
     * @param msg Mensaje a mostrar por consola
     * @return BigDecimal ingresado en consola
     */
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

    /**
     * Lee un valor por consola, en caso de que la entrada esté vacía retorna el valor original.
     * @param msg Mensaje a mostrar por consola
     * @param actualValue Valor actual
     * @return Nuevo valor.
     */
    public String editString(String msg, String actualValue) {
        System.out.printf("%s [%s]: ", msg, actualValue);

        String input = SCANNER.nextLine().trim();

        // Si está vacío, devuelve el valor viejo. Si no, el nuevo.
        if (input.isEmpty()) {
            return actualValue;
        }
        return input;
    }

    /**
     * Lee un valor por consola, en caso de que la entrada esté vacía retorna el valor original.
     * @param msg Mensaje a mostrar por consola
     * @param actualValue Valor actual
     * @return Nuevo valor.
     */
    public long editLong(String msg, Long actualValue) {
        while (true) {
            System.out.printf("%s [%d]: ", msg, actualValue);
            String input = SCANNER.nextLine().trim();

            if (input.isEmpty()) {
                return actualValue;
            }

            try {
                return Long.parseLong(input);

            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número entero válido.");
            }
        }
    }

    /**
     * Lee un valor por consola, en caso de que la entrada esté vacía retorna el valor original.
     * @param msg Mensaje a mostrar por consola
     * @param actualValue Valor actual
     * @return Nuevo valor.
     */
    public int editInt(String msg, int actualValue) {
        while (true) {
            System.out.printf("%s [%d]: ", msg, actualValue);
            String input = SCANNER.nextLine().trim();

            if (input.isEmpty()) {
                return actualValue;
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

    /**
     * Lee un valor por consola, en caso de que la entrada esté vacía retorna el valor original.
     * @param msg Mensaje a mostrar por consola
     * @param actualValue Valor actual
     * @return Nuevo valor.
     */
    public BigDecimal editBigDecimal(String msg, BigDecimal actualValue) {
        while (true) {
            System.out.printf("%s [%s]: ", msg, actualValue);
            String input = SCANNER.nextLine().trim();

            if (input.isEmpty()) {
                return actualValue;
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

    /**
     * Muestra el mensaje "Presiona enter para continuar..."
     * y espera a que se presione enter para continuar
     */
    public void awaitInput(){
        System.out.println("Presiona enter para continuar...");
        SCANNER.nextLine();
    }
}
