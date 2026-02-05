package com.ecommerce.demo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Builder para Tablas, utiliza el patrón fluent builder para la creación de las mismas.
 *
 * @param <T> Tipo de la clase
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class TableBuilder<T> {

    private final List<String> footers;
    private final List<Column<T>> columns;
    private final List<T> data;
    private String title;

    /**
     * Constructor de ConsoleTable
     */
    private TableBuilder() {
        this.footers = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    /**
     * Crea un nuevo TableBuilder del tipo T
     *
     * @param objectClass Clase del objeto
     * @param <T>         Tipo de la clase
     * @return Nuevo TableBuilder del tipo T
     */
    public static <T> TableBuilder<T> of(Class<T> objectClass) {
        return new TableBuilder<>();
    }

    /**
     * Añade una nueva columna a la tabla
     *
     * @param header    Cabecera de la tabla
     * @param width     Ancho de la tabla
     * @param extractor Extractor de la columna (Ej: getNombre)
     * @return La misma instancia de {@see ConsoleTable} para permitir el encadenamiento.
     */
    public TableBuilder<T> addColumn(String header, int width, Function<T, Object> extractor) {
        columns.add(new Column<>(header, width, extractor));
        return this;
    }

    /**
     * Estable el título  de la tabla
     *
     * @param title Título de la tabla
     * @return La misma instancia de {@see ConsoleTable} para permitir el encadenamiento.
     */
    public TableBuilder<T> setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Establece los datos
     *
     * @param data Datos
     * @return La misma instancia de {@see ConsoleTable} para permitir el encadenamiento.
     */
    public TableBuilder<T> setData(List<T> data) {
        this.data.addAll(data);
        return this;
    }

    /**
     * Estable el pie de la tabla
     *
     * @param footers Pie de tabla
     * @return La misma instancia de {@see ConsoleTable} para permitir el encadenamiento.
     */
    public TableBuilder<T> addFooter(String footers) {
        this.footers.add(footers);
        return this;
    }

    /**
     * Itera sobre una lista de elementos para añadirlo al footer.
     *
     * @param elements Lista de los elementos
     * @param mapper   Función que define cómo convertir cada elemento {@code E} en un {@code String}.
     * @param <E>      Tipo de los elementos de la table (puede ser distinto al de la tabla)
     * @return La misma instancia de {@see ConsoleTable} para permitir el encadenamiento.
     */
    public <E> TableBuilder<T> addFooters(List<E> elements, Function<E, String> mapper) {
        if (elements != null) {
            elements.forEach(item -> this.addFooter(mapper.apply(item)));
        }
        return this;
    }

    /**
     * @return Una nueva instancia {@see ConsoleTable}
     */
    public TableResult<T> build() {
        return new TableResult<>(title, footers, columns, data);
    }

    /**
     *
     * @param header    Nombre de la columna
     * @param width     Ancho de la columna
     * @param extractor Extractor de la columna (Ej: getNombre)
     * @param <T>       Tipo de la tabla
     */
    public record Column<T>(String header, int width, Function<T, Object> extractor) {
    }


}
