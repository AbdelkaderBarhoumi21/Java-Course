package Discount;

public final class FixedDiscount implements DiscountStrategy {
    private final double amount;

    FixedDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double apply(double subtotal) {
        return Math.min(amount, subtotal);
    }

    @Override
    public String describe() {
        return "%.2f fixed off".formatted(amount);
    }
}