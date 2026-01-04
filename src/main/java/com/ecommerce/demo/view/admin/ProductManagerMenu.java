package com.ecommerce.demo.view.admin;

import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.CategoryService;
import com.ecommerce.demo.service.ProductService;
import com.ecommerce.demo.view.AbstractMenu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ProductManagerMenu extends AbstractMenu {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductManagerMenu(Scanner scanner, ProductService productService, CategoryService categoryService) {
        super(scanner);
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    protected void printMenuOptions() {
        //TODO averiguar a que se refiere con: Buscar (nombre/categoría)
        System.out.println("""
                ----------------------
                        ADMIN
                     Menú Producto
                ----------------------
                0) Salir
                1) Listar productos
                2) Buscar
                3) Crear producto
                4) Editar producto
                5) Eliminar producto
                """);
    }

    @Override
    protected boolean handleOption(int option) {
        switch (option) {
            case 0 -> {
                return false;
            }
            case 1 -> findAll();
            case 2 -> search();
            case 3 -> create();
            case 4 -> edit();
            case 5 -> delete();
            default -> System.out.println("Opción inválida");
        }
        return true;
    }

    private void findAll() {
        List<ProductSummaryDto> products = productService.findAllSummary();
        for (ProductSummaryDto product : products) {
            System.out.println(product);
        }
    }

    private void search() {

    }

    public void create() {
        System.out.println("Categorías disponibles: ");
        for (Category category : categoryService.findAll()) {
            System.out.println(category);
        }

        System.out.println();
        String name = readText("Ingresa el nombre del producto: ");
        Long categoryId = readLong("Ingresa la id de la categoría: ");
        BigDecimal price = readBigDecimal("Ingresa el precio del producto: ");
        Product product = new Product(categoryId, price, name);
        //TODO pensar si es necesario permitir o no un valor negativo
        int stock = readInt("Cantidad de stock: ");

        try {
            productService.create(product, stock);
        } catch (Exception e) {
            System.out.println("No se ha creado el producto debido a que: " + e.getMessage());
        }
    }

    private void edit() {

    }

    private void delete() {
        long id = readLong("Ingresa la id del producto a eliminar: ");
        try {
            String confirmDelete = readText("¿Seguro que quieres eliminar el producto con id = " + id + "? (Sí/No): ");
            if (confirm(confirmDelete)) {
                productService.deleteById(id);
                System.out.println("Producto eliminado");
            } else {
                //prácticamente el "No" del texto está de adorno
                System.out.println("No se ha eliminado el producto");
            }
        } catch (Exception e) {
            System.out.println("No se ha podido eliminar el producto debido a que: " + e.getMessage());
        }
    }
}
