
package Discount;

public interface DiscountStrategy {
    double apply(double subtotal);

    String describe();
}