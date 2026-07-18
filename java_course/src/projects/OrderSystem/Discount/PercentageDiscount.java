package Discount;

public final class PercentageDiscount implements DiscountStrategy {
    private final double percent;

    PercentageDiscount(double percent) {
        if (percent < 0 || percent > 100)
            throw new IllegalArgumentException("Percent must be 0-100");
        this.percent = percent;
    }

    public double percent() {
        return percent;
    }

    @Override
    public double apply(double subtotal) {
        return subtotal * percent / 100.0;
    }

    @Override
    public String describe() {
        return "%.0f%% off".formatted(percent);
    }

}