package service;

import adapter.PaymentProcessor;
import model.Cart;
import observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private Cart cart;
    private PaymentProcessor paymentProcessor;

    private List<OrderObserver> observers;

    public OrderService(Cart cart) {
        this.cart = cart;
        this.observers = new ArrayList<>();
    }

    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void confirmOrder() {
        double total = cart.calculateTotal();

        System.out.println("\n--- Confirmando pedido ---");
        paymentProcessor.pay(total);

        String message = "Compra confirmada por S/ " + total;
        System.out.println(message);

        notifyObservers(message);
    }

    private void notifyObservers(String message) {
        System.out.println("\n--- Notificaciones ---");
        for (OrderObserver observer : observers) {
            observer.update(message);
        }
    }
}
