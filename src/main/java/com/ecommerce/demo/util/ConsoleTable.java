package com.ecommerce.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Clase de utilidad para imprimir tablas, utiliza el patrón fuelnt design para la creación de las mismas.
 *
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class ConsoleTable<T> {

    /**
     *
     * @param header Nombre de la columna
     * @param width Ancho de la columna
     * @param extractor Extractor de la columna (ej: getNombre)
     * @param <T> Tipo de la tabla
     */
    private record Column<T>(String header, int width, Function<T, Object> extractor) {}

    private String title;
    private final List<Column<T>> columns = new ArrayList<>();

    /**
     * Añade una nueva columna a la tabla
     * @param header Cabecera de la tabla
     * @param width Ancho de la tabla
     * @param extractor Extractor de la columna (ej: getNombre)
     * @return La misma instancia de {@see ConsoleTable} para permitir el encadenamiento.
     */
    public ConsoleTable<T> addColumn(String header, int width, Function<T, Object> extractor) {
        columns.add(new Column<>(header, width, extractor));
        return this;
    }

    /**
     * Estable el título  de la tabla
     *<p>
     *     Las columnas se imprimirán en el mismo orden en que son añadidas.
     *</p>
     * <h3>Ejemplo de uso:</h3>
     * <pre>
     table
         .addColumn("ID", 4, ProductSummaryDto::id)
         .addColumn("PRODUCTO", 20, ProductSummaryDto::name)
         .addColumn("CATEGORÍA", 15, ProductSummaryDto::category)
     * </pre>
     *
     * @param title Título de la tabla
     * @return La misma instancia de {@see ConsoleTable} para permitir el encadenamiento.
     */
    public ConsoleTable<T> setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Imprime la tabla con el título y las columnas previamente establecidas.
     * El formato de los datos se construye con las columnas que se haya añadido previamente.
     * <p>
     *     En caso de que no haya datos, se imprime un mensaje de sin datos.
     *     Si no se ha establecido el título, no se muestra.
     * <p>
     *     Ejemplo: si previamente se ha utilizado para construir la tabla
     * <pre>
     *     new ConsoleTable<ProductSummaryDto>()
     *                 .setTitle("PRODUCTO")
     *                 .addColumn("ID", 4, ProductSummaryDto::id)
     *                 .addColumn("PRODUCTO", 20, ProductSummaryDto::name)
     *                 .addColumn("CATEGORÍA", 15, ProductSummaryDto::category)
     *                 .addColumn("PRECIO", 10, ProductSummaryDto::price)
     *                 .addColumn("STOCK", 5, ProductSummaryDto::stock)
     *      Se crea el string para el formato: | %-4s | %-20s | %-15s | %-10s | %-5s |%n
     *      Mostrando como resultado:
     *      +--------------------------------------------------------------------+
     *      | PRODUCTO                                                           |
     *      +------+----------------------+-----------------+------------+-------+
     *      | ID   | PRODUCTO             | CATEGORÍA       | PRECIO     | STOCK |
     *      +------+----------------------+-----------------+------------+-------+
     *      | 1    | Lentes de sol        | Accesorio       | 25000      | 12    |
     *      | 2    | Mochila              | Accesorio       | 100000     | 32    |
     *      | 3    | Carpa                | Expedición      | 55000      | 14    |
     *      | 4    | Polera térmica       | Vestimenta      | 50000      | 25    |
     *      +------+----------------------+-----------------+------------+-------+
     * </pre>
     * @param data Datos a mostrar
     */
    public void print(List<T> data) {
        if (data.isEmpty()) {
            System.out.println("""
                    +------------------+
                    |   Sin datos      |
                    +------------------+
                    """);
            return;
        }


        String format = columns.stream()
                .map(c -> "| %-" + c.width() + "s ")
                .collect(Collectors.joining()) + "|%n";

        /* Construcción del borde de la tabla (antes: +--+--+)  */
        String border = columns.stream()
                .map(c -> "+" + "-".repeat(c.width() + 2))
                .collect(Collectors.joining()) + "+";

        if (title != null) {
            // techo del título
            System.out.println("+" + "-".repeat(border.length() - 2) + "+");

            //centrar el texto implica añadir más métodos, lo dejo simple por el momento
            //podría pasar que necesite truncar el título, mejor intento evitarlo
            String pre = "| " + title;
            String end = " ".repeat(border.length() - 1 - pre.length()) + "|";
            System.out.println(pre + end);
        }

        System.out.println(border);
        var headers = columns.stream().map(Column::header).toArray();
        System.out.printf(format, headers);
        System.out.println(border);

        // 4. Imprimir Filas
        for (T item : data) {
            Object[] values = columns.stream()
                    .map(c -> truncate(String.valueOf(c.extractor().apply(item)), c.width()))
                    .toArray();
            System.out.printf(format, values);
        }

        System.out.println(border);
    }

    /**
     * Trunca un texto al ancho establecido
     *
     * @param text  Texto a truncar
     * @param width Cantidad máxima de caracteres.
     * @return La misma instancia de {@see ConsoleTable}..
     */
    private String truncate(String text, int width) {
        if (text == null) return "";
        return text.length() > width ? text.substring(0, width - 3) + "..." : text;
    }
}
