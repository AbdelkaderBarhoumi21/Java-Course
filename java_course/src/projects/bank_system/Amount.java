package projects.bank_system;

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
}