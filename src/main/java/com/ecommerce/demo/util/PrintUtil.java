package com.ecommerce.demo.util;

import com.ecommerce.demo.dto.*;
import com.ecommerce.demo.model.Category;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Clase de utilidad para imprimir mensajes formateados en la consola
 *
 * @author Gabriel Norambuena
 */
public class PrintUtil {
    public record MenuOption(String key, String description) {
    }

    /**
     * Función de utilidad para imprimir los productos de forma formateada
     * <p> Muestra: ID, PRODUCTO, CATEGORÍA, PRECIO y STOCK </ṕ>
     *
     * @param products Lista con los productos a imprimir
     * @see ConsoleTable
     */
    public static void printProductSummary(List<ProductSummaryDto> products) {
        new ConsoleTable<ProductSummaryDto>()
                .setTitle("PRODUCTO")
                .addColumn("ID", 4, ProductSummaryDto::id)
                .addColumn("PRODUCTO", 20, ProductSummaryDto::name)
                .addColumn("CATEGORÍA", 15, ProductSummaryDto::category)
                .addColumn("PRECIO", 10, ProductSummaryDto::price)
                .addColumn("STOCK", 5, ProductSummaryDto::stock)
                .print(products);
    }

    /**
     * Función de utilidad para imprimir un producto determinado.
     * Utiliza la implementación de {@link #printProductSummary(List)}
     *
     * @param product Producto a imprimir
     * @see ConsoleTable
     */
    public static void printProductSummary(ProductSummaryDto product) {
        printProductSummary(List.of(product));
    }

    /**
     * Función de utilidad para imprimir las categorías de forma formateada
     * <p> Muestra: ID, CATEGORÍA </ṕ>
     *
     * @param categories Lista con las Categorías a mnostrar
     * @see ConsoleTable
     */
    public static void printCategories(List<Category> categories) {
        new ConsoleTable<Category>()
                .setTitle("CATEGORÍA")
                .addColumn("ID", 3, Category::getId)
                .addColumn("CATEGORÍA", 30, Category::getName)
                .print(categories);

    }

    /**
     * Imprime el menú principal
     *
     * @see ConsoleTable
     */
    public static void printMainMenu() {
        List<MenuOption> options = List.of(
                new MenuOption("1", "Administrador"),
                new MenuOption("2", "Usuario"),
                new MenuOption("0", "Salir")
        );

        new ConsoleTable<MenuOption>()
                .setTitle("MENÚ PRINCIPAL")
                .addColumn("OPCIÓN", 8, MenuOption::key)
                .addColumn("DESCRIPCIÓN", 25, MenuOption::description)
                .print(options);
    }

    /**
     * Imprime el menú de administrador
     *
     * @see ConsoleTable
     */
    public static void printAdminMenu() {
        List<MenuOption> options = List.of(
                new MenuOption("1", "Listar productos"),
                new MenuOption("2", "Buscar (nombre/categoría)"),
                new MenuOption("3", "Crear producto"),
                new MenuOption("4", "Editar producto"),
                new MenuOption("5", "Eliminar producto"),
                new MenuOption("0", "Salir")
        );

        new ConsoleTable<MenuOption>()
                .setTitle("MENÚ ADMINISTRADOR")
                .addColumn("OPCIÓN", 8, MenuOption::key)
                .addColumn("DESCRIPCIÓN", 25, MenuOption::description)
                .print(options);
    }

    /**
     * Imprime el menú de usuario
     *
     * @see ConsoleTable
     */
    public static void printUserMenu() {
        List<MenuOption> options = List.of(
                new MenuOption("1", "Listar productos"),
                new MenuOption("2", "Buscar productos"),
                new MenuOption("3", "Agregar al carrito"),
                new MenuOption("4", "Quitar del carro"),
                new MenuOption("5", "Ver carrito"),
                new MenuOption("6", "Ver descuentos activos"),
                new MenuOption("0", "Salir")
        );

        new ConsoleTable<MenuOption>()
                .setTitle("MENÚ USUARIO")
                .addColumn("OPCIÓN", 8, MenuOption::key)
                .addColumn("DESCRIPCIÓN", 25, MenuOption::description)
                .print(options);
    }

    /**
     * Imprime el resumen del carro
     *
     * @param cartSummary Resumen del carro
     * @see ConsoleTable
     */
    public static void printCartItems(CartSummary cartSummary) {
        new ConsoleTable<CartProductsDto>()
                .setTitle("CARRITO")
                .addColumn("ID", 4, CartProductsDto::ID)
                .addColumn("PRODUCTO", 20, CartProductsDto::name)
                .addColumn("PRECIO UNITARIO", 17, CartProductsDto::unitPrice)
                .addColumn("CANTIDAD", 10, CartProductsDto::quantity)
                .addColumn("SUBTOTAL", 10, CartProductsDto::subTotal)
                .setFooter("TOTAL BASE: " + cartSummary.total())
                .print(cartSummary.products());
    }

    /**
     * Imprime los descuentos activos
     *
     * @param discounts Descuentos activos
     * @see ConsoleTable
     */
    public static void printDiscounts(DiscountSummary discounts) {
        new ConsoleTable<AppliedDiscount>()
                .setTitle("DESCUENTOS")
                .addColumn("NOMBRE", 50, AppliedDiscount::ruleName)
                .addColumn("PORCENTAJE", 12, AppliedDiscount::amount)
                .setFooter("DESCUENTOS TOTALES (%): " + discounts.totalDiscount())
                .print(discounts.discounts());

    }
}
