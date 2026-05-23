package adapter;

//Simula una librería de terceros, tiene una firma incompatible con PaymentProcessor
public class ExternalPayPalService {
    public void makePayment(String currency, double amount) {
        System.out.println("Pago realizado con Paypal: " + currency + " " + amount);
    }
}
