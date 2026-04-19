package oop;

/**
 * Encapsulation: Encapsulation means protecting a class's data by making fields
 * private and exposing public methods (getters/setters) to access or modify
 * them.
 *
 * Access modifiers: - private : accessible only inside the same class - default
 * : accessible within the same package (no keyword) - protected : accessible
 * within the same package and by subclasses - public : accessible from anywhere
 *
 * Benefits: - Hides internal implementation details. - Lets you validate values
 * before changing state (e.g., reject a negative balance). - Lets you change
 * the internal representation later without breaking callers.
 */
public class encapsulation {

    public static void main(String[] args) {
        BankAccount account = new BankAccount("Alice", 1000);
        System.out.println("Holder: " + account.getHolder());
        System.out.println("Initial balance: " + account.getBalance());

        account.deposit(500);
        account.withdraw(200);

        System.out.println("New balance: " + account.getBalance());
    }
}

class BankAccount {

    // PRIVATE attributes — not directly accessible from outside
    private String holder;
    private double balance;

    // Constructor — initializes the fields when the object is created
    public BankAccount(String holder, double initialBalance) {
        this.holder = holder;
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    // Getters — allow read access to the private fields
    public String getHolder() {
        return holder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposited: " + amount + " | New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds for this withdrawal.");
        }
        balance -= amount;
        System.out.println("Withdrew: " + amount + " | New balance: " + balance);
    }
}
