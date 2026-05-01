package projects.PaymentSystem;

// S — Single Responsibility
// Each class has one job

class PaymentValidator {
    boolean validate(double amount) {
        return amount > 0;
    }
}

class PaymentLogger {
    void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

class ReceiptGenerator {
    void generate(String method, double amount) {
        System.out.println("=== Receipt ===");
        System.out.println("Method : " + method);
        System.out.println("Amount : " + amount);
        System.out.println("===============");
    }
}

// O — Open/Closed + D — Dependency Inversion
// Add new payment methods without touching PaymentProcessor

interface PaymentMethod {
    void process(double amount);

    String name();
}

class CreditCard implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Charging credit card: " + amount);

    }

    @Override
    public String name() {
        return "Credit card";
    }
}

class Paypal implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Sending via PayPal: " + amount);

    }

    @Override
    public String name() {
        return "PayPal";
    }
}

class Crypto implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Sending crypto: " + amount);
    }

    @Override
    public String name() {
        return "Crypto";
    }
}

// I — Interface Segregation
// Refundable is separate — not all payments support refunds

interface Refundable {
    void refund(double amount);
}

class CreditCardWithRefund extends CreditCard implements Refundable {
    @Override
    public void refund(double amount) {
        System.out.println("Refunding to credit card: " + amount);
    }
}

// D — Dependency Inversion
// PaymentProcessor depends on abstractions, not concrete classes

class PaymentProcessor {
    private final PaymentMethod method; // abstraction
    private final PaymentValidator validator;
    private final PaymentLogger logger;
    private final ReceiptGenerator receiptGenerator;

    // Dependencies injected from outside
    public PaymentProcessor(PaymentMethod method, PaymentValidator validator, PaymentLogger logger,
            ReceiptGenerator receiptGenerator) {
        this.method = method;
        this.validator = validator;
        this.logger = logger;
        this.receiptGenerator = receiptGenerator;

    }

    void pay(double amount) {
        if (!validator.validate(amount)) {
            logger.log("Invalid amount: " + amount);
            return;
        }
        method.process(amount);
        receiptGenerator.generate(method.name(), amount);
    }
}

public class PaymentSystem {
    public static void main(String[] args) {

        PaymentValidator validator = new PaymentValidator();
        PaymentLogger logger = new PaymentLogger();
        ReceiptGenerator receiptGenerator = new ReceiptGenerator();

        PaymentProcessor crediPaymentProcessor = new PaymentProcessor(new CreditCard(), validator, logger,
                receiptGenerator);

        PaymentProcessor paypalPaymentProcessor = new PaymentProcessor(new Paypal(), validator, logger,
                receiptGenerator);

        PaymentProcessor cryptoPaymentProcessor = new PaymentProcessor(new Crypto(), validator, logger,
                receiptGenerator);
        crediPaymentProcessor.pay(500);
        paypalPaymentProcessor.pay(600);
        cryptoPaymentProcessor.pay(-100);

    }

}
