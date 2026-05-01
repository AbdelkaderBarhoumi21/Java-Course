package projects.BankSystem;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double amount) {
        super("Insufficient funds. Tried to withdraw: " + amount);
    }
}