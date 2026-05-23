import adapter.ExternalPayPalService;
import adapter.PayPalAdapter;
import model.Cart;
import model.Product;
import observer.AdminNotificationObserver;
import observer.EmailNotificationObserver;
import observer.InventoryObserver;
import service.OrderService;
import strategy.PercentageDiscountStrategy;

public class Main {
    public static void main(String[] args) {
        // 1. Crear productos
        Product l1 = new Product("Laptop Dell Inspiron 3520 (Intel Core i7):", 2900.00, 1);
        Product m1 = new Product("Mouse Logitech MX MASTER 3S WIRELESS 8K USB-C GRAPHITE", 560.00, 2);
        Product t1 = new Product("Teclado Redragon Kumara K552", 180, 4);

        // 2. Agregar productos al carrito
        Cart cart = new Cart();
        cart.addProduct(l1);
        cart.addProduct(m1);
        cart.addProduct(t1);

        // 3. Aplicar estrategia de descuento
        cart.setDiscountStrategy(new PercentageDiscountStrategy(10));
        cart.showCart();

        // 4. Procesar pago usando Adapter
        OrderService orderService = new OrderService(cart);
        orderService.setPaymentProcessor(new PayPalAdapter(new ExternalPayPalService()));

        // 5. Confirmar orden
        orderService.addObserver(new EmailNotificationObserver());
        orderService.addObserver(new InventoryObserver());
        orderService.addObserver(new AdminNotificationObserver());

        // 6. Notificar observadores
        orderService.confirmOrder();
    }
}