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
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final InventoryService inventoryService;

    /**
     * Constructor de la clase
     * @param productRepository Repositorio de los productos
     * @param categoryService Servicio de las categorías
     * @param inventoryService Servicio de inventario
     */
    public ProductService(ProductRepository productRepository, CategoryService categoryService, InventoryService inventoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.inventoryService = inventoryService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Producto", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean notExistsById(Long id) {
        return !productRepository.existsById(id);
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
        if (categoryService.notExistsById(product.getCategoryId())) {
            throw new ResourceNotFoundException("Categoría", product.getCategoryId());
        }
        if(product.getName() == null || product.getName().isBlank()){
            throw new IllegalArgumentException("El nombre del producto es inválido");
        }
        //si es <= 0 significa que el precio es menor o igual a 0
        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El precio no puede ser <= 0");
        }
        Product createdProduct = productRepository.save(product);

        Inventory inventory = new Inventory(createdProduct.getId(), quantity);
        inventoryService.create(inventory);
        return createdProduct;
    }

    /**
     * Obtiene un resumen de todos los productos
     * @return Listado con resumen de todos los productos
     */
    public List<ProductSummaryDto> findAllSummary() {
        List<Product> products = productRepository.findAll();
        List<ProductSummaryDto> res = new ArrayList<>();

        //NOTA no es la forma más óptima, sin embargo, el rendimiento no es un requisito
        for (Product product : products) {
            var category = categoryService.getById(product.getCategoryId());
            var inventory = inventoryService.getByProductId(product.getId());
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
        Inventory inventory = inventoryService.getById(product.getId());
        Category category = categoryService.getById(product.getCategoryId());
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
        productRepository.deleteById(id);
        inventoryService.deleteById(product.getCategoryId());
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
        if (categoryService.notExistsById(product.getCategoryId())) {
            throw new ResourceNotFoundException("Categoría", product.getCategoryId());
        }

        if(product.getName() == null || product.getName().isBlank()){
            throw new IllegalArgumentException("El nombre del producto es inválido");
        }

        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El precio no puede ser <= 0");
        }
        Product existing = getById(id);

        inventoryService.updateByProductId(id, stock);
        existing.setName(product.getName());
        existing.setCategoryId(product.getCategoryId());
        existing.setPrice(product.getPrice());

        return productRepository.save(existing);
    }

    /**
     * Busca productos por medio de la palabra clave, buscando en su nombre y categoría.
     * @param searchText Texto a buscar
     * @return Listado de productos coincidentes
     */
    public List<ProductSummaryDto> search(String searchText) {
        String searchLower = searchText.toLowerCase();

        List<Long> matchingCategoryIds = categoryService.findIdsByName(searchText);
        List<Product> matches = productRepository.findAll().stream()
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
        String catName = categoryService.getById(p.getCategoryId()).getName();
        int stock = inventoryService.getById(p.getId()).getQuantity();
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
