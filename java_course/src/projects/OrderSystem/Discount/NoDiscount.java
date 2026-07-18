package Discount;

public final class NoDiscount implements DiscountStrategy {
    @Override
    public double apply(double subtotal) {
        return 0.0;
    }

    @Override
    public String describe() {
        return "No discount is applied";
    }
}