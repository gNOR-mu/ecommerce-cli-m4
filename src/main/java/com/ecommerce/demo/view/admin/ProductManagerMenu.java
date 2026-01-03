package com.ecommerce.demo.view.admin;

import com.ecommerce.demo.service.ProductService;
import com.ecommerce.demo.view.AbstractMenu;

import java.util.Scanner;

public class ProductManagerMenu extends AbstractMenu {
    private final ProductService productService;

    public ProductManagerMenu(Scanner scanner, ProductService productService) {
        super(scanner);
        this.productService = productService;
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
            case 1 -> listAll();
            case 2 -> search();
            case 3 -> create();
            case 4 -> edit();
            case 5 -> delete();
            default -> System.out.println("Opción inválida");
        }
        return true;
    }

    private void listAll() {

    }

    private void search() {

    }

    public void create() {

    }

    private void edit() {

    }

    private void delete() {

    }
}
