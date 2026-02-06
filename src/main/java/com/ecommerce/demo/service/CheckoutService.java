package com.ecommerce.demo.service;

import java.math.BigDecimal;

import com.ecommerce.demo.dto.CartProductsDto;
import com.ecommerce.demo.dto.CartSummary;
import com.ecommerce.demo.dto.CheckoutSummaryDto;
import com.ecommerce.demo.dto.DiscountSummary;
import com.ecommerce.demo.exceptions.InvalidOperationException;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.Order;
import com.ecommerce.demo.model.OrderItem;

/**
 * Servicio para procesar las transacciones
 */
public class CheckoutService {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final DiscountCalculatorService discountCalculatorService;
    private final CartService cartService;
    private final InventoryService inventoryService;

    /**
     * Constructor del servicio de pago
     *
     * @param orderService              Servicio de la orden
     * @param orderItemService          Servicio de orderItem
     * @param discountCalculatorService Servicio de cálculo de descuentos
     * @param cartService               Servicio del carrito
     * @param inventoryService          Servicio del inventario
     */
    public CheckoutService(OrderService orderService,
            OrderItemService orderItemService,
            DiscountCalculatorService discountCalculatorService, CartService cartService,
            InventoryService inventoryService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.discountCalculatorService = discountCalculatorService;
        this.cartService = cartService;
        this.inventoryService = inventoryService;
    }

    /**
     * Obtiene un resumen del carrito antes de realizar el pago.
     * <p>
     * Contiene:
     * <ul>
     * <li>Todos los productos a pagar</li>
     * <li>Detalle de descuentos aplicados</li>
     * <li>Total base</li>
     * <li>Total final</li>
     * </ul>
     *
     * @param cart Carrito
     * @return Detalle de la compra
     * @throws InvalidOperationException Cuando el total base es inferior o igual a 0
     * @throws InvalidOperationException Cuando se intenta aplicar descuentos negativos
     */
    public CheckoutSummaryDto getSummary(Cart cart) {
        if (cart == null || cart.getAllItems().isEmpty()) {
            throw new InvalidOperationException("El carro no puede estar vacío");
        }
        CartSummary cartSummary = cartService.getAll(cart);
        DiscountSummary discountSummary = discountCalculatorService.applyDiscount(cart);
        BigDecimal finalPrice = calculateFinalPrice(cartSummary.total(), discountSummary.totalDiscount());

        return new CheckoutSummaryDto(cartSummary, discountSummary, finalPrice);
    }

    /**
     * Calcula el precio final.
     *
     * @param totalBase Precio total calculado antes de descuentos.
     * @param discounts Descuento total a aplicar.
     * @return Total luego de aplicar el descuento
     * @throws InvalidOperationException Cuando el total base es inferior o igual a 0
     * @throws InvalidOperationException Cuando se intenta aplicar descuentos negativos
     * @apiNote No considera límite máximo para los descuentos, en caso de que el
     *          descuento sea mayor a 1 (100%)
     *          simplemente el precio total es cero
     * @apiNote Los descuentos se definen en porcentaje, por lo que es necesario
     *          dividirlo por 100
     * @apiNote El totalBase debe ser mayor a 0
     *
     */
    private BigDecimal calculateFinalPrice(BigDecimal totalBase, BigDecimal discounts) {
        // equivalente a dividir por 100
        discounts = discounts.movePointLeft(2);
        /* El precio total base no puede ser <= 0 */
        if (totalBase.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOperationException("El total base no puede ser menor o igual a 0");
        }

        /* No hay descuentos negativos */
        if (discounts.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidOperationException(("No pueden haber descuentos negativos"));
        }

        /*
         * Si por alguna razón tengo un descuento mayor o igual a 100%, solo retorno el
         * costo total 0,
         * no hay límite para descuento máximo.
         */
        if (discounts.compareTo(BigDecimal.ONE) >= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal discount = totalBase.multiply(discounts);
        return totalBase.subtract(discount);
    }

    /**
     * Procesa la compra
     *
     * @param cart Carro a pagar
     * @throws InvalidOperationException Cuando el total base es inferior o igual a 0
     * @throws InvalidOperationException Cuando se intenta aplicar descuentos negativos
     */
    public void checkout(Cart cart) {
        CartSummary cartSummary = cartService.getAll(cart);
        DiscountSummary discountSummary = discountCalculatorService.applyDiscount(cart);
        BigDecimal finalPrice = calculateFinalPrice(cartSummary.total(), discountSummary.totalDiscount());

        // verifica que se pueda reducir el inventario antes de hacer cualquier cosa
        cartSummary.products().stream()
                .filter(p -> !inventoryService.isPossibleSubtract(p.Id(), p.quantity()))
                .findFirst()
                .ifPresent(p -> {
                    throw new InvalidOperationException("No se puede reducir el inventario de " + p.name());
                });

        Order order = new Order(cartSummary.total(),
                finalPrice,
                discountSummary.totalDiscount(),
                discountSummary.formattedDiscounts());
        Order createdOrder = orderService.create(order);

        /*
         * Por cada producto de la orden, creo un order item para mantener el registro
         */
        for (CartProductsDto product : cartSummary.products()) {
            inventoryService.subtractInventory(product.Id(), product.quantity());
            OrderItem orderItem = new OrderItem(
                    product.Id(),
                    createdOrder.getId(),
                    product.subTotal(),
                    product.unitPrice(),
                    product.quantity());
            orderItemService.create(orderItem);
        }

        /* Finalmente vacío el carrito */
        cartService.clear(cart);
    }

}
