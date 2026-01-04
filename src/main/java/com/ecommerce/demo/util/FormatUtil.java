package com.ecommerce.demo.util;

import com.ecommerce.demo.dto.ProductSummaryDto;

import java.util.List;

public class FormatUtil {
    private static final String PRODUCT_SUMMARY_FORMAT = "| %-4s | %-20s | %-15s | %10s | %5s |%n";
    private static final String SEPARATOR = "+------+----------------------+-----------------+------------+-------+";

    public static String truncateText(String text, int maxSize) {
        //así evito que se rompa la tabla
        if (text == null) return "";
        if (text.length() <= maxSize) return text;
        return text.substring(0, maxSize - 3) + "...";
    }

    public static void printProductSummary(List<ProductSummaryDto> products) {
        // Imprimir Cabecera
        System.out.println(SEPARATOR);
        System.out.printf(PRODUCT_SUMMARY_FORMAT, "ID", "NOMBRE", "CATEGORÍA", "PRECIO", "STOCK");
        System.out.println(SEPARATOR);

        // Imprimir Filas
        for (ProductSummaryDto product : products) {
            System.out.printf(PRODUCT_SUMMARY_FORMAT,
                    product.id(),
                    truncateText(product.name(), 20),
                    truncateText(product.category(), 15),
                    product.price(),
                    product.stock());
        }

        // Imprimir Pie
        System.out.println(SEPARATOR);
    }

    public static void printProductSummary(ProductSummaryDto product) {
        printProductSummary(List.of(product));
    }
}
