package projects.BankSystem;

public class CheckingAccount extends Amount {
    private final double overdraftLimit;

    public CheckingAccount(String owner, double balance, double overdraftLimit) {
        super(owner, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > getBalance() + overdraftLimit) {
            throw new InsufficientFundsException(amount);
        }
        // Direct access to balance is not possible as it is private in Amount,
        // but for this logic we would typically use a protected field or a setter.
        // Assuming standard implementation based on the provided Amount class:
        deposit(-amount);
        System.out.println("Withdrew: " + amount + " (Overdraft used if balance negative)");
    }

    @Override
    public String accountType() {
        return "Checking Account";
    }

}
