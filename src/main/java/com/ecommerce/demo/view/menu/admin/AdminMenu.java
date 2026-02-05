package com.ecommerce.demo.view.menu.admin;

import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.enums.AdminMenuOptions;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.CategoryService;
import com.ecommerce.demo.service.ProductService;
import com.ecommerce.demo.util.MenuOption;
import com.ecommerce.demo.util.PrintUtil;
import com.ecommerce.demo.util.InputHandler;
import com.ecommerce.demo.view.menu.AbstractMenu;

import java.math.BigDecimal;
import java.util.List;

/**
 * Menú de administrador
 *
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class AdminMenu extends AbstractMenu {
    private final ProductService productService;
    private final CategoryService categoryService;

    /**
     * @param inputHandler    Clase de utilidad para manejar las entradas.
     * @param productService  Servicio de productos
     * @param categoryService Servicio de la categoría
     */
    public AdminMenu(InputHandler inputHandler, ProductService productService, CategoryService categoryService) {
        super(inputHandler);
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void printMenuOptions() {
        PrintUtil.printAdminMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean handleOption(int code) {
        AdminMenuOptions option = MenuOption.find(AdminMenuOptions.class, code).orElse(null);

        switch (option) {
            case LIST_PRODUCTS -> listProducts();
            case SEARCH_PRODUCT -> searchProduct();
            case CREATE_PRODUCT -> createProduct();
            case UPDATE_PRODUCT -> updateProduct();
            case DELETE_PRODUCT -> delete();
            case EXIT -> {
                return false;
            }
            case null, default -> System.out.println("Opción inválida");
        }
        inputHandler.awaitInput();
        return true;
    }

    /**
     * Lista todos los productos.
     */
    private void listProducts() {
        List<ProductSummaryDto> products = productService.findAllSummary();
        PrintUtil.printProductSummary(products);
    }

    /**
     * Busca un producto por nombre/categoría
     */
    private void searchProduct() {
        String searchText = inputHandler.readText("Texto a buscar nombre/categoría: ");
        List<ProductSummaryDto> res = productService.search(searchText);
        PrintUtil.printProductSummary(res);
    }

    /**
     * Crea un producto nuevo.
     */
    public void createProduct() {
        List<Category> categories = categoryService.findAll();

        String name = inputHandler.readText("Ingresa el nombre del producto: ");

        PrintUtil.printCategories(categories);
        Long categoryId = inputHandler.readLong("Ingresa la id de la categoría: ");
        BigDecimal price = inputHandler.readBigDecimal("Ingresa el precio del producto: ");
        Product product = new Product(categoryId, price, name);
        int stock = inputHandler.readInt("Cantidad de stock: ");

        try {
            productService.create(product, stock);
            System.out.println("Producto creado exitosamente.");
        } catch (IllegalArgumentException | ResourceNotFoundException e) {
            System.out.println("Datos inválidos: " + e.getMessage());
        }
    }

    /**
     * Actualiza un producto
     */
    private void updateProduct() {
        long id = inputHandler.readLong("Ingresa la id del producto a editar: ");

        try {
            ProductSummaryDto product = productService.getSummaryById(id);
            List<Category> categories = categoryService.findAll();
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
            productService.update(id, updatedProduct, stock);

            System.out.println("Producto actualizado");
        } catch (Exception e) {
            System.out.println("No se ha actualizado el producto debido a que: " + e.getMessage());
        }
    }

    /**
     * Elimina un producto
     */
    private void delete() {
        long id = inputHandler.readLong("Ingresa la id del producto a eliminar: ");
        try {
            ProductSummaryDto product = productService.getSummaryById(id);
            PrintUtil.printProductSummary(product);
            String confirmDelete = inputHandler.readText("¿Seguro que quieres eliminar el producto? (Sí/No): ");

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
