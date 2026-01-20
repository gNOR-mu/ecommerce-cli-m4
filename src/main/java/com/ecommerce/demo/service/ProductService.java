package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la gestión de productos
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class ProductService implements IdentifiableService<Product, Long> {
    private final ProductRepository PRODUCT_REPOSITORY;
    private final CategoryService CATEGORY_SERVICE;
    private final InventoryService INVENTORY_SERVICE;

    /**
     * Constructor de la clase
     * @param productRepository Repositorio de los productos
     * @param categoryService Servicio de las categorías
     * @param inventoryService Servicio de inventario
     */
    public ProductService(ProductRepository productRepository, CategoryService categoryService, InventoryService inventoryService) {
        this.PRODUCT_REPOSITORY = productRepository;
        this.CATEGORY_SERVICE = categoryService;
        this.INVENTORY_SERVICE = inventoryService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product getById(Long id) {
        return PRODUCT_REPOSITORY.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Producto", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean notExistsById(Long id) {
        return !PRODUCT_REPOSITORY.existsById(id);
    }

    /**
     * Crea un nuevo producto
     * @param product Producto a crear
     * @param quantity Cantidad del producto
     * @return Nuevo producto
     *
     * @throws ResourceNotFoundException Cuando la categoría no existe
     * @throws IllegalArgumentException Cuando el nombre es inválido
     * @throws IllegalArgumentException Cuando el precio es <= 0
     */
    public Product create(Product product, int quantity) {
        if (CATEGORY_SERVICE.notExistsById(product.getCategoryId())) {
            throw new ResourceNotFoundException("Categoría", product.getCategoryId());
        }
        if(product.getName() == null || product.getName().isBlank()){
            throw new IllegalArgumentException("El nombre del producto es inválido");
        }
        //si es <= 0 significa que el precio es menor o igual a 0
        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El precio no puede ser <= 0");
        }
        Product createdProduct = PRODUCT_REPOSITORY.save(product);

        Inventory inventory = new Inventory(createdProduct.getId(), quantity);
        INVENTORY_SERVICE.create(inventory);
        return createdProduct;
    }

    /**
     * Obtiene un resumen de todos los productos
     * @return Listado con resumen de todos los productos
     */
    public List<ProductSummaryDto> findAllSummary() {
        List<Product> products = PRODUCT_REPOSITORY.findAll();
        List<ProductSummaryDto> res = new ArrayList<>();

        //NOTA no es la forma más óptima, sin embargo, el rendimiento no es un requisito
        for (Product product : products) {
            var category = CATEGORY_SERVICE.getById(product.getCategoryId());
            var inventory = INVENTORY_SERVICE.getByProductId(product.getId());
            res.add(new ProductSummaryDto(
                    product.getId(),
                    product.getName(),
                    category.getName(),
                    product.getPrice(),
                    inventory.getQuantity(),
                    product.getCategoryId()));
        }

        return res;
    }

    /**
     * Obtiene el resumen de un producto determinado
     * @param id Identificación del producto
     * @return Resumen del producto
     *
     * @throws ResourceNotFoundException Viene de {@link #getById(Long)}
     */
    public ProductSummaryDto getSummaryById(Long id) {
        Product product = getById(id);
        Inventory inventory = INVENTORY_SERVICE.getById(product.getId());
        Category category = CATEGORY_SERVICE.getById(product.getCategoryId());
        return new ProductSummaryDto(product.getId(), product.getName(), category.getName(), product.getPrice(),
                inventory.getQuantity(), product.getCategoryId());
    }


    /**
     * Elimina un producto
     * @param id Identificación del producto
     *
     * @throws ResourceNotFoundException Viene de {@link #getById(Long)}
     * @throws com.ecommerce.demo.exceptions.InventoryException Viene de {@link InventoryService#deleteById(Long)}

     */
    public void deleteById(Long id) {
        Product product = getById(id);
        PRODUCT_REPOSITORY.deleteById(id);
        INVENTORY_SERVICE.deleteById(product.getCategoryId());
    }

    /**
     * Actualiza un producto
     * @param id Identificación del producto
     * @param product Producto
     * @param stock Nuevo stock
     * @return Producto actualizado
     *
     * @throws ResourceNotFoundException Viene de {@link #getById(Long)}
     * @throws ResourceNotFoundException Cuando no encuentra la categoría.
     * @throws IllegalArgumentException Cuando el inventario es inferior a 0, viene de {@link InventoryService#updateByProductId(Long, int)}
     */
    public Product update(Long id, Product product, int stock) {
        if (CATEGORY_SERVICE.notExistsById(product.getCategoryId())) {
            throw new ResourceNotFoundException("Categoría", product.getCategoryId());
        }

        if(product.getName() == null || product.getName().isBlank()){
            throw new IllegalArgumentException("El nombre del producto es inválido");
        }

        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El precio no puede ser <= 0");
        }
        Product existing = getById(id);

        INVENTORY_SERVICE.updateByProductId(id, stock);
        existing.setName(product.getName());
        existing.setCategoryId(product.getCategoryId());
        existing.setPrice(product.getPrice());

        return PRODUCT_REPOSITORY.save(existing);
    }

    /**
     * Busca productos por medio de la palabra clave, buscando en su nombre y categoría.
     * @param searchText Texto a buscar
     * @return Listado de productos coincidentes
     */
    public List<ProductSummaryDto> search(String searchText) {
        String searchLower = searchText.toLowerCase();

        List<Long> matchingCategoryIds = CATEGORY_SERVICE.findIdsByName(searchText);
        List<Product> matches = PRODUCT_REPOSITORY.findAll().stream()
                .filter(p -> {
                    boolean nameMatches = p.getName().toLowerCase().contains(searchLower);
                    boolean categoryMatches = matchingCategoryIds.contains(p.getCategoryId());
                    return nameMatches || categoryMatches;
                })
                .toList();

        return matches.stream().map(this::convertToDto).toList();
    }

    /**
     * Convierte un producto a {@link ProductSummaryDto}
     * @param p Producto a convertir
     * @return Un resumen del producto
     */
    private ProductSummaryDto convertToDto(Product p) {
        //prácticamente es como un mapper
        String catName = CATEGORY_SERVICE.getById(p.getCategoryId()).getName();
        int stock = INVENTORY_SERVICE.getById(p.getId()).getQuantity();
        return new ProductSummaryDto(
                p.getId(),
                p.getName(),
                catName,
                p.getPrice(),
                stock,
                p.getCategoryId()
        );
    }
}
