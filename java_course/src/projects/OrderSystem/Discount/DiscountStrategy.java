
package Discount;

interface DiscountStrategy {
    double apply(double subtotal);

    String describe();
}