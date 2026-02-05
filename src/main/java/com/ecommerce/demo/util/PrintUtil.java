package com.ecommerce.demo.util;

import com.ecommerce.demo.discount.DiscountRule;
import com.ecommerce.demo.dto.*;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.view.TableBuilder;

import java.util.List;

/**
 * Clase de utilidad para imprimir mensajes formateados en la consola
 *
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class PrintUtil {
    /**
     * Registro con las opciones de un menú
     *
     * @param key         Opción
     * @param description Descripción
     */
    private record MenuOption(String key, String description) {
    }

    /**
     * Función de utilidad para imprimir los productos de forma formateada
     * <p> Muestra: ID, PRODUCTO, CATEGORÍA, PRECIO y STOCK </ṕ>
     *
     * @param products Lista con los productos a imprimir
     * @see TableBuilder
     */
    public static void printProductSummary(List<ProductSummaryDto> products) {
        TableBuilder.of(ProductSummaryDto.class)
                .setTitle("PRODUCTO")
                .addColumn("ID", 4, ProductSummaryDto::id)
                .addColumn("PRODUCTO", 20, ProductSummaryDto::name)
                .addColumn("CATEGORÍA", 15, ProductSummaryDto::category)
                .addColumn("PRECIO", 10, ProductSummaryDto::price)
                .addColumn("STOCK", 5, ProductSummaryDto::stock)
                .setData(products)
                .build()
                .print();
    }

    /**
     * Función de utilidad para imprimir un producto determinado.
     * Utiliza la implementación de {@link #printProductSummary(List)}
     *
     * @param product Producto a imprimir
     * @see TableBuilder
     */
    public static void printProductSummary(ProductSummaryDto product) {
        printProductSummary(List.of(product));
    }

    /**
     * Función de utilidad para imprimir las categorías de forma formateada
     * <p> Muestra: ID, CATEGORÍA </ṕ>
     *
     * @param categories Lista con las Categorías a mostrar
     * @see TableBuilder
     */
    public static void printCategories(List<Category> categories) {
        TableBuilder.of(Category.class)
                .setTitle("CATEGORÍA")
                .addColumn("ID", 3, Category::getId)
                .addColumn("CATEGORÍA", 30, Category::getName)
                .setData(categories)
                .build()
                .print();
    }

    /**
     * Imprime el menú principal
     *
     * @see TableBuilder
     */
    public static void printMainMenu() {
        //TODO MOVER
        List<MenuOption> options = List.of(
                new MenuOption("1", "Administrador"),
                new MenuOption("2", "Usuario"),
                new MenuOption("0", "Salir")
        );

        TableBuilder.of(MenuOption.class)
                .setTitle("MENÚ PRINCIPAL")
                .addColumn("OPCIÓN", 8, MenuOption::key)
                .addColumn("DESCRIPCIÓN", 25, MenuOption::description)
                .setData(options)
                .build()
                .print();
    }

    /**
     * Imprime el menú de administrador
     *
     * @see TableBuilder
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

        TableBuilder.of(MenuOption.class)
                .setTitle("MENÚ ADMINISTRADOR")
                .addColumn("OPCIÓN", 8, MenuOption::key)
                .addColumn("DESCRIPCIÓN", 25, MenuOption::description)
                .setData(options)
                .build()
                .print();
    }

    /**
     * Imprime el menú de usuario
     *
     * @see TableBuilder
     */
    public static void printUserMenu() {
        List<MenuOption> options = List.of(
                new MenuOption("1", "Listar productos"),
                new MenuOption("2", "Buscar productos"),
                new MenuOption("3", "Agregar al carrito"),
                new MenuOption("4", "Quitar del carro"),
                new MenuOption("5", "Ver carrito"),
                new MenuOption("6", "Ver descuentos activos"),
                new MenuOption("7", "Confirmar compra"),
                new MenuOption("0", "Salir")
        );

        TableBuilder.of(MenuOption.class)
                .setTitle("MENÚ USUARIO")
                .addColumn("OPCIÓN", 8, MenuOption::key)
                .addColumn("DESCRIPCIÓN", 25, MenuOption::description)
                .setData(options)
                .build()
                .print();
    }

    /**
     * Imprime el resumen del carro
     *
     * @param cartSummary Resumen del carro
     * @see TableBuilder
     */
    public static void printCartItems(CartSummary cartSummary) {
        TableBuilder.of(CartProductsDto.class)
                .setTitle("CARRITO")
                .addColumn("ID", 4, CartProductsDto::Id)
                .addColumn("PRODUCTO", 20, CartProductsDto::name)
                .addColumn("PRECIO UNITARIO", 17, CartProductsDto::unitPrice)
                .addColumn("CANTIDAD", 10, CartProductsDto::quantity)
                .addColumn("SUBTOTAL", 10, CartProductsDto::subTotal)
                .addFooter("TOTAL BASE: " + cartSummary.total())
                .setData(cartSummary.products())
                .build()
                .print();
    }

    /**
     * Imprime los descuentos activos
     *
     * @param discounts Descuentos activos
     * @see TableBuilder
     */
    public static void printDiscounts(DiscountSummary discounts) {
        TableBuilder.of(AppliedDiscount.class)
                .setTitle("DESCUENTOS")
                .addColumn("NOMBRE", 50, AppliedDiscount::ruleName)
                .addColumn("PORCENTAJE (%)", 16, AppliedDiscount::amount)
                .addFooter("DESCUENTOS TOTALES (%): " + discounts.totalDiscount())
                .setData(discounts.discounts())
                .build()
                .print();
    }

    /**
     * Imprime los descuentos activos
     *
     * @param discounts Descuentos activos
     * @see TableBuilder
     */
    public static void printDiscounts(List<DiscountRule> discounts) {
        TableBuilder.of(DiscountRule.class)
                .setTitle("DESCUENTOS")
                .addColumn("NOMBRE", 50, DiscountRule::getName)
                .addColumn("CONDICIÓN", 50, DiscountRule::getCondition)
                .addColumn("PORCENTAJE", 12, DiscountRule::calculateDiscount)
                .setData(discounts)
                .build()
                .print();

    }

    /**
     * Imprime un resumen del pago
     * @param checkoutSummaryDto Resumen del pago
     */
    public static void printCheckoutSummary(CheckoutSummaryDto checkoutSummaryDto) {
        TableBuilder.of(CartProductsDto.class)
                .setTitle("CARRITO A PAGAR")
                .addColumn("ID", 4, CartProductsDto::Id)
                .addColumn("PRODUCTO", 25, CartProductsDto::name)
                .addColumn("PRECIO UNITARIO", 25, CartProductsDto::unitPrice)
                .addColumn("CANTIDAD", 10, CartProductsDto::quantity)
                .addColumn("SUBTOTAL", 10, CartProductsDto::subTotal)
                .addFooters(checkoutSummaryDto.discountSummary().discounts(), discount ->
                        "%s: %s (%s%%)".formatted(discount.ruleName(), discount.condition(), discount.amount())
                )
                .addFooter("DESCUENTOS TOTALES: %s%%".formatted(checkoutSummaryDto.discountSummary().totalDiscount()))
                .addFooter("TOTAL BASE: " + checkoutSummaryDto.cartSummary().total())
                .addFooter("TOTAL FINAL: " + checkoutSummaryDto.finalPrice())
                .setData(checkoutSummaryDto.cartSummary().products())
                .build()
                .print();
    }
}
