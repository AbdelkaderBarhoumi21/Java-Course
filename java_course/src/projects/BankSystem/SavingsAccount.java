package projects.BankSystem;

public class SavingsAccount extends Amount {
    private final double interestRate;

    public SavingsAccount(String owner, double balance, double interestRate) {
        super(owner, balance);
        this.interestRate = interestRate;

    }

    public void applyInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.println("Interest applied: " + interest);
    }

    @Override
    public String accountType() {
        return "Savings account";
    }

}
