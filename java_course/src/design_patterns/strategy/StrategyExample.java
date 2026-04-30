package design_patterns.strategy;

/*
 * Strategy Pattern
 * ----------------
 * A family of interchangeable algorithms behind ONE interface.
 * The client picks the strategy at runtime — no if/else ladder.
 *
 *   PaymentService(new CreditCardPayment()).pay(100);
 *   PaymentService(new PaypalPayment()).pay(100);
 *
 * When to use:
 *   - Several ways to do the same job (sort, pay, compress, route).
 *   - You want to add new ways without modifying existing code (OCP).
 */
public class StrategyExample {

    public static void main(String[] args) {
        new PaymentService(new CreditCardPayment()).pay(100);
        new PaymentService(new PaypalPayment()).pay(50);
        new PaymentService(new CryptoPayment()).pay(25);
    }
}

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " by credit card");
    }
}

class PaypalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via PayPal");
    }
}

class CryptoPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " in crypto");
    }
}

class PaymentService {
    private final PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(double amount) {
        strategy.pay(amount);
    }
}
