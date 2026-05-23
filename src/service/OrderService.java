package service;

import adapter.PaymentProcessor;
import model.Cart;
import observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private Cart cart;
    private PaymentProcessor paymentProcessor;

    //Lista de sistemas suscritos que serán notificados al confirmar la compra
    private List<OrderObserver> observers;

    public OrderService(Cart cart) {
        this.cart = cart;
        this.observers = new ArrayList<>();
    }

    //Establece el método de pago activo (activo o adapter)
    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    //Confirma el pedido ejecutando el pago y notificando a los observadores
    public void confirmOrder() {
        double total = cart.calculateTotal();

        System.out.println("\n--- Confirmando pedido ---");
        paymentProcessor.pay(total);

        String message = "Compra confirmada por S/ " + total;
        System.out.println(message);

        notifyObservers(message);
    }

    //Recorre la lista de observadores y notifica a cada uno
    private void notifyObservers(String message) {
        System.out.println("\n--- Notificaciones ---");
        for (OrderObserver observer : observers) {
            observer.update(message);
        }
    }
}
