package strategy;

public class FixedAmountDiscountStrategy implements DiscountStrategy {
    private double fixedAmount;

    public FixedAmountDiscountStrategy(double fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    @Override
    public double applyDiscount(double total) {
        double result = Math.max(0, total - fixedAmount);
        System.out.println("Descuento fijo aplicado: S/ " + fixedAmount);
        return result;
    }
}
