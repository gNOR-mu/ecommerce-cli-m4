import repository.*;
import repository.impl.*;

public class Main {

    public static void main(String[] args) {
        //inicialización de los datos como BD simulado en memoria
        CategoryRepository categoryRepository = new InMemoryCategoryRepository();
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        InventoryRepository inventoryRepository = new InMemoryInventoryRepository();
        OrderItemRepository orderItemRepository = new InMemoryOrderItemRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();
        PaymentRepository paymentRepository = new InMemoryPaymentRepository();
        ProductRepository productRepository = new InMemoryProductRepository();
    }
}
