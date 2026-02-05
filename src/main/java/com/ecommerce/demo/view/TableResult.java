package com.ecommerce.demo.view;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Resultado del build de {@link TableBuilder}
 *
 * @param <T> Tipo de la tabla
 */
public class TableResult<T> {
    private final String title;
    private final List<String> footers;
    private final List<TableBuilder.Column<T>> columns;
    private final List<T> data;
    private final String border;
    private final String format;

    /**
     * Constructor de TableResult
     *
     * @param title   Título de la tabla
     * @param footers Pies de la tabla
     * @param columns Columnas de la tabla
     * @param data    Datos de la tabla
     */
    public TableResult(String title, List<String> footers, List<TableBuilder.Column<T>> columns, List<T> data) {
        this.title = title;
        this.columns = List.copyOf(columns);
        this.data = List.copyOf(data);
        this.footers = (footers.isEmpty() && data.isEmpty()) ? List.of("Nada encontrado...") : List.copyOf(footers);
        this.format = buildFormat(this.columns);
        this.border = buildBorder(this.columns);
    }

    /**
     * Construye un string con el formato para dibujar el borde según el ancho establecido para las columnas.
     * <p>
     * Ejemplo: | %-4s | %-20s | %-17s | %-10s | %-10s |%n
     *
     * @param columns Columnas sobre las que construir el formato
     * @return String con el formato de las columnas
     */
    private String buildFormat(List<TableBuilder.Column<T>> columns) {
        return columns.stream()
                .map(c -> "| %-" + c.width() + "s ")
                .collect(Collectors.joining()) + "|%n";
    }

    /**
     * Construye el borde de la tabla a partir de las columnas
     *
     * @param columns Columnas sobre las que construir el borde
     * @return String representando el borde
     */
    private String buildBorder(List<TableBuilder.Column<T>> columns) {
        /* Construcción del borde de la tabla (ej: +--+--+)  */
        return columns.stream()
                .map(c -> "+" + "-".repeat(c.width() + 2))
                .collect(Collectors.joining()) + "+";
    }

    /**
     * Imprime la tabla con el título y las columnas previamente establecidas.
     * El formato de los datos se construye con las columnas que se haya añadido previamente.
     */
    public void print() {
        printTitle();
        printBody();
        printFooter();
    }

    /**
     * Trunca un texto al ancho establecido
     *
     * @param text  Texto a truncar
     * @param width Cantidad máxima de caracteres.
     * @return La misma instancia de {@see ConsoleTable}..
     */
    private String truncate(String text, int width) {
        if (text == null) {
            return "";
        }
        return text.length() > width ? text.substring(0, width - 3) + "..." : text;
    }

    /**
     * Imprime el título de la tabla
     *
     */
    private void printTitle() {
        if (title == null) {
            return;
        }
        // techo del título
        System.out.println("+" + "-".repeat(border.length() - 2) + "+");

        //centrar el texto implica añadir más métodos, lo dejo simple por el momento
        //podría pasar que necesite truncar el título, mejor intento evitarlo
        String pre = "| " + title;
        String end = " ".repeat(border.length() - 1 - pre.length()) + "|";
        System.out.println(pre + end);
    }

    /**
     * Imprime el cuerpo de la tabla
     */
    private void printBody() {
        System.out.println(border);

        if (data.isEmpty()) {
            return;
        }
        var headers = columns.stream().map(TableBuilder.Column::header).toArray();
        System.out.printf(format, headers);
        System.out.println(border);

        // Imprimir Filas
        for (T item : data) {
            Object[] values = columns.stream()
                    .map(c -> truncate(String.valueOf(c.extractor().apply(item)), c.width()))
                    .toArray();
            System.out.printf(format, values);
        }

        System.out.println(border);
    }

    /**
     * Imprime el pie de tabla
     *
     */
    private void printFooter() {
        if (footers.isEmpty()) {
            return;
        }

        for (String footer : footers) {
            int padding = border.length() - 2 - footer.length();
            String spaces = " ".repeat(Math.max(0, padding));
            System.out.println("|" + spaces + footer + "|");
        }
        System.out.println(border);
    }
}
