package com.ecommerce.demo.view.admin;

import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.CategoryService;
import com.ecommerce.demo.service.ProductService;
import com.ecommerce.demo.util.PrintUtil;
import com.ecommerce.demo.util.InputHandler;
import com.ecommerce.demo.view.AbstractMenu;

import java.math.BigDecimal;
import java.util.List;

public class AdminMenu extends AbstractMenu {
    private final ProductService PRODUCT_SERVICE;
    private final CategoryService CATEGORY_SERVICE;

    public AdminMenu(InputHandler inputHandler, ProductService productService, CategoryService categoryService) {
        super(inputHandler);
        this.PRODUCT_SERVICE = productService;
        this.CATEGORY_SERVICE = categoryService;
    }

    @Override
    protected void printMenuOptions() {
        PrintUtil.printAdminMenu();
    }

    @Override
    protected boolean handleOption(int option) {
        switch (option) {
            case 0 -> {
                return false;
            }
            case 1 -> listProducts();
            case 2 -> searchProduct();
            case 3 -> createProduct();
            case 4 -> updateProduct();
            case 5 -> delete();
            default -> System.out.println("Opción inválida");
        }
            inputHandler.awaitInput();
        return true;
    }

    private void listProducts() {
        List<ProductSummaryDto> products = PRODUCT_SERVICE.findAllSummary();
        PrintUtil.printProductSummary(products);
    }

    private void searchProduct() {
        String searchText = inputHandler.readText("Texto a buscar nombre/categoría: ");
        List<ProductSummaryDto> res = PRODUCT_SERVICE.search(searchText);
        PrintUtil.printProductSummary(res);
    }

    public void createProduct() {
        List<Category> categories = CATEGORY_SERVICE.findAll();

        String name = inputHandler.readText("Ingresa el nombre del producto: ");

        PrintUtil.printCategories(categories);
        Long categoryId = inputHandler.readLong("Ingresa la id de la categoría: ");
        BigDecimal price = inputHandler.readBigDecimal("Ingresa el precio del producto: ");
        Product product = new Product(categoryId, price, name);
        int stock = inputHandler.readInt("Cantidad de stock: ");

        try {
            PRODUCT_SERVICE.create(product, stock);
            System.out.println("Producto creado exitosamente.");
        } catch (Exception e) {
            System.out.println("No se ha creado el producto debido a que: " + e.getMessage());
        }
    }

    private void updateProduct() {
        long id = inputHandler.readLong("Ingresa la id del producto a editar: ");

        try {
            ProductSummaryDto product = PRODUCT_SERVICE.getSummaryById(id);
            List<Category> categories = CATEGORY_SERVICE.findAll();
            PrintUtil.printProductSummary(product);

            //modificaciones
            System.out.println("Presiona enter para dejar el campo sin modificar");
            String name = inputHandler.editString("Producto", product.name());

            PrintUtil.printCategories(categories);
            Long categoryId = inputHandler.editLong("Categoría", product.categoryId());
            BigDecimal price = inputHandler.editBigDecimal("Precio", product.price());
            int stock = inputHandler.editInt("Stock", product.stock());

            //actualización
            Product updatedProduct = new Product(categoryId, price, name);
            PRODUCT_SERVICE.update(id, updatedProduct, stock);

            System.out.println("Producto actualizado");
        } catch (Exception e) {
            System.out.println("No se ha actualizado el producto debido a que: " + e.getMessage());
        }
    }

    private void delete() {
        long id = inputHandler.readLong("Ingresa la id del producto a eliminar: ");
        try {
            String confirmDelete = inputHandler.readText("¿Seguro que quieres eliminar el producto con id = " + id + "? (Sí/No): ");
            if (confirm(confirmDelete)) {
                PRODUCT_SERVICE.deleteById(id);
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
