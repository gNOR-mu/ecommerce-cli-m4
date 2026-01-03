import repository.*;
import repository.impl.*;
import service.*;

public class DependencyContainer {
    private final CategoryRepository CATEGORY_REPOSITORY;
    private final CustomerRepository CUSTOMER_REPOSITORY;
    private final InventoryRepository INVENTORY_REPOSITORY;
    private final OrderItemRepository ORDER_ITEM_REPOSITORY;
    private final OrderRepository ORDER_REPOSITORY;
    private final PaymentRepository PAYMENT_REPOSITORY;
    private final ProductRepository PRODUCT_REPOSITORY;

    private final CategoryService CATEGORY_SERVICE;
    private final CustomerService CUSTOMER_SERVICE;
    private final PaymentService PAYMENT_SERVICE;
    private final ProductService PRODUCT_SERVICE;
    private final OrderService ORDER_SERVICE;
    private final OrderItemService ORDER_ITEM_SERVICE;

    public DependencyContainer() {
        this.CATEGORY_REPOSITORY = new InMemoryCategoryRepository();
        this.CUSTOMER_REPOSITORY = new InMemoryCustomerRepository();
        this.INVENTORY_REPOSITORY = new InMemoryInventoryRepository();
        this.ORDER_ITEM_REPOSITORY = new InMemoryOrderItemRepository();
        this.ORDER_REPOSITORY = new InMemoryOrderRepository();
        this.PAYMENT_REPOSITORY = new InMemoryPaymentRepository();
        this.PRODUCT_REPOSITORY = new InMemoryProductRepository();

        this.CATEGORY_SERVICE = new CategoryService(CATEGORY_REPOSITORY);
        this.CUSTOMER_SERVICE = new CustomerService(CUSTOMER_REPOSITORY);
        this.PAYMENT_SERVICE = new PaymentService(PAYMENT_REPOSITORY);
        this.PRODUCT_SERVICE = new ProductService(PRODUCT_REPOSITORY, CATEGORY_SERVICE);
        this.ORDER_SERVICE = new OrderService(ORDER_REPOSITORY, CUSTOMER_SERVICE, PAYMENT_SERVICE);
        this.ORDER_ITEM_SERVICE = new OrderItemService(ORDER_ITEM_REPOSITORY, ORDER_SERVICE, PRODUCT_SERVICE);
    }

    public CategoryRepository getCATEGORY_REPOSITORY() {
        return CATEGORY_REPOSITORY;
    }

    public CustomerRepository getCUSTOMER_REPOSITORY() {
        return CUSTOMER_REPOSITORY;
    }

    public InventoryRepository getINVENTORY_REPOSITORY() {
        return INVENTORY_REPOSITORY;
    }

    public OrderItemRepository getORDER_ITEM_REPOSITORY() {
        return ORDER_ITEM_REPOSITORY;
    }

    public OrderRepository getORDER_REPOSITORY() {
        return ORDER_REPOSITORY;
    }

    public PaymentRepository getPAYMENT_REPOSITORY() {
        return PAYMENT_REPOSITORY;
    }

    public ProductRepository getPRODUCT_REPOSITORY() {
        return PRODUCT_REPOSITORY;
    }

    public CategoryService getCATEGORY_SERVICE() {
        return CATEGORY_SERVICE;
    }

    public CustomerService getCUSTOMER_SERVICE() {
        return CUSTOMER_SERVICE;
    }

    public PaymentService getPAYMENT_SERVICE() {
        return PAYMENT_SERVICE;
    }

    public ProductService getPRODUCT_SERVICE() {
        return PRODUCT_SERVICE;
    }

    public OrderService getORDER_SERVICE() {
        return ORDER_SERVICE;
    }

    public OrderItemService getORDER_ITEM_SERVICE() {
        return ORDER_ITEM_SERVICE;
    }
}
