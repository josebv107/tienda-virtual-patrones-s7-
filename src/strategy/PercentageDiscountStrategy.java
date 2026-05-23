package strategy;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private double percentage;

    public PercentageDiscountStrategy(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double total) {
        double discount = total * (percentage / 100);
        System.out.println("Descuento aplicado: " + percentage + "% = S/ " + discount);
        return total - discount;
    }
}
