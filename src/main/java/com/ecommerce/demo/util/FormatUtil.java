package com.ecommerce.demo.util;

import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.model.Category;

import java.util.List;

public class FormatUtil {
    private static final String PRODUCT_SUMMARY_FORMAT = "| %-4s | %-20s | %-15s | %10s | %5s |%n";
    private static final String PRODUCT_BORDER = "+------+----------------------+-----------------+------------+-------+";

    private static final String CATEGORY_FORMAT = "| %-4s | %-30s |%n";
    private static final String CATEGORY_BORDER = "+------+--------------------------------+";

    public static String truncateText(String text, int maxSize) {
        //así evito que se rompa la tabla
        if (text == null) return "";
        if (text.length() <= maxSize) return text;
        return text.substring(0, maxSize - 3) + "...";
    }

    public static void printProductSummary(List<ProductSummaryDto> products) {
        if (products.isEmpty()) {
            System.out.println("""
                    +-------------------------------------+
                    |  ️  No hay productos registrados.     |
                    +-------------------------------------+
                    """);
            return;
        }

        System.out.println(PRODUCT_BORDER);
        System.out.printf(PRODUCT_SUMMARY_FORMAT, "ID", "PRODUCTO", "CATEGORÍA", "PRECIO", "STOCK");
        System.out.println(PRODUCT_BORDER);

        // Imprimir Filas
        for (ProductSummaryDto product : products) {
            System.out.printf(PRODUCT_SUMMARY_FORMAT,
                    product.id(),
                    truncateText(product.name(), 20),
                    truncateText(product.category(), 15),
                    product.price(),
                    product.stock());
        }

        System.out.println(PRODUCT_BORDER);
    }

    public static void printProductSummary(ProductSummaryDto product) {
        printProductSummary(List.of(product));
    }

    public static void printCategories(List<Category> categories) {
        if (categories.isEmpty()) {
            System.out.println("""
                    +-------------------------------------+
                    |  ️  No hay categorías registradas.    |
                    +-------------------------------------+
                    """);
            System.out.println(CATEGORY_BORDER);
            System.out.println("|  ️  No hay categorías registradas.   |");
            System.out.println(CATEGORY_BORDER);
            return;
        }

        System.out.println(CATEGORY_BORDER);
        System.out.printf(CATEGORY_FORMAT, "ID", "CATEGORÍA");
        System.out.println(CATEGORY_BORDER);

        for (Category c : categories) {
            System.out.printf(CATEGORY_FORMAT,
                    c.getId(),
                    truncateText(c.getName(), 30)
            );
        }

        // Cierre
        System.out.println(CATEGORY_BORDER);
    }


    public static void printCategory(Category category) {
        printCategories(List.of(category));
    }
}
