package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.ProductSummaryDto;
import com.ecommerce.demo.exceptions.InvalidOperationException;
import com.ecommerce.demo.exceptions.ResourceNotFoundException;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Inventory;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.*;

/**
 * Servicio para la gestión de productos
 *
 * @author Gabriel Norambuena
 * @version 1.0
 */
public class ProductService implements IdentifiableService<Product, Long> {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final InventoryService inventoryService;

    //ordenamiento por precio asc
    private final Comparator<ProductSummaryDto> comparatorPrice = Comparator.comparing(ProductSummaryDto::price);

    /**
     * Constructor de la clase
     *
     * @param productRepository Repositorio de los productos
     * @param categoryService   Servicio de las categorías
     * @param inventoryService  Servicio de inventario
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
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto", id));
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
     *
     * @param product  Producto a crear
     * @param quantity Cantidad del producto
     * @return Nuevo producto
     * @throws ResourceNotFoundException Cuando la categoría no existe
     * @throws InvalidOperationException Cuando el nombre es inválido
     * @throws InvalidOperationException Cuando el precio es <= 0
     */
    public Product create(Product product, int quantity) {
        validateFields(product);
        // 1. crea el producto
        Product createdProduct = productRepository.save(product);

        // 2. creo su inventario
        try {
            Inventory inventory = new Inventory(createdProduct.getId(), quantity);
            inventoryService.create(inventory);
        } catch (Exception e) {
            //lo debería eliminar si hay algún problema
            // en una bd se revertiría con el rollback
            deleteById(product.getId());
            throw e;
        }

        return createdProduct;
    }


    /**
     * Obtiene un resumen de todos los productos
     *
     * @return Listado con resumen de todos los productos
     */
    public List<ProductSummaryDto> findAllSummary() {
        List<Product> products = productRepository.findAll();

        // NOTA no es la forma más óptima, sin embargo, el rendimiento no es un requisito
        // problema n + 1 al convertir varios productos con categorías repetidas

        return convertToDto(products);
    }

    /**
     * Obtiene todos los productos con stock
     *
     * @return Una lista con los productos con stock
     */
    public List<ProductSummaryDto> findAllWithStock() {
        return findAllSummary().stream()
                .filter(p -> p.stock() > 0)
                .sorted(Comparator.comparing(ProductSummaryDto::price))
                .toList();
    }

    /**
     * Obtiene el resumen de un producto determinado
     *
     * @param id Identificación del producto
     * @return Resumen del producto
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
     *
     * @param id Identificación del producto
     * @throws ResourceNotFoundException Viene de {@link #getById(Long)}
     * @throws IllegalArgumentException  Viene de {@link InventoryService#deleteByProductId(Long)}}
     */
    public void deleteById(Long id) {
        inventoryService.deleteByProductId(id);
        productRepository.deleteById(id);
    }

    /**
     * Actualiza un producto
     *
     * @param id      Identificación del producto
     * @param product Producto
     * @param stock   Nuevo stock
     * @return Producto actualizado
     * @throws ResourceNotFoundException Viene de {@link #getById(Long)}
     * @throws ResourceNotFoundException Cuando no encuentra la categoría.
     * @throws InvalidOperationException Cuando el inventario es inferior a 0, viene de {@link InventoryService#updateByProductId(Long, int)}
     */
    public Product update(Long id, Product product, int stock) {
        validateFields(product);

        Product existing = getById(id);

        inventoryService.updateByProductId(id, stock);
        existing.setName(product.getName());
        existing.setCategoryId(product.getCategoryId());
        existing.setPrice(product.getPrice());

        return productRepository.save(existing);
    }

    /**
     * Busca productos por medio de la palabra clave, buscando en su nombre y categoría.
     *
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

        return convertToDto(matches);
    }

    /**
     * Convierte los productos a una lista ordenada de {@link ProductSummaryDto} y los ordena
     *
     * @param products Listado de productos
     * @return Un resumen ordenado de los productos
     */
    private List<ProductSummaryDto> convertToDto(List<Product> products) {
        return products.stream().map(p -> {
                    String categoryName = categoryService.getById(p.getCategoryId()).getName();
                    int stock = inventoryService.getById(p.getId()).getQuantity();

                    return new ProductSummaryDto(
                            p.getId(),
                            p.getName(),
                            categoryName,
                            p.getPrice(),
                            stock,
                            p.getCategoryId()
                    );
                }).sorted(comparatorPrice)
                .toList();
    }

    /**
     * Valida que los datos del producto a crear o actualizar sean válidos
     *
     * @apiNote No se valida un nombre repetido ya que es viable
     * @param product Producto a validar
     */
    private void validateFields(Product product) {
        Category category = categoryService.getById(product.getCategoryId());

        if (product.getName() == null || product.getName().isBlank()) {
            throw new InvalidOperationException("El nombre del producto es inválido");
        }

        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOperationException("El precio no puede ser <= 0");
        }

        product.setCategoryId(category.getId());

    }
}
