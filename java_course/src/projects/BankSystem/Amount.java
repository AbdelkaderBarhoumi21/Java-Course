package projects.BankSystem;

public abstract class Amount {
    private String owner;
    private double balance;

    public Amount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    // Encapsulation — balance is private, controlled via methods
    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance)
            throw new InsufficientFundsException(amount);
        balance -= amount;
        System.out.println("Withdrew: " + amount + "| New balance");
    }

    // Each subclass describes itself differently
    public abstract String accountType();

    @Override
    public String toString() {
        return accountType() + " | Owner: " + owner + " | Balance: " + balance;
    }
}