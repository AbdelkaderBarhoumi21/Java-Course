package projects.BankAccountSystem;

import java.util.*;

// balance = 100.0
// on essaie de retirer 150.0
// shortfall = 150.0 - 100.0 = 50.0

// --- Custom exception (checked) ---
class InsufficientFundsException extends Exception {
    private final double shortfall;

    public InsufficientFundsException(double requested, double available) {
        super("Requested %.2f but only %.2f available".formatted(requested, available));
        this.shortfall = requested - available;
    }

    public double getShortfall() {
        return shortfall;
    }
}

// --- Capability interface ---
interface Transferable {
    void transferTo(Account target, double amount) throws InsufficientFundsException;

}

// --- Abstraction: cannot instantiate Account directly ---
abstract class Account implements Transferable {
    private final String id;
    protected double balance;

    protected Account(String id, double initial) {
        // throw new => new to create the exception object : throw execute the exception
        // and new create the exception object
        // without new => you havent create the exception object
        if (initial < 0)
            throw new IllegalArgumentException("Initial balance cannot be negative or < 0 ");
        this.id = id;
        this.balance = initial;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        balance += amount;
    }

    // Each subclass defines its own withdrawal rules
    // CheckingAccount — can go negative up to -500 (overdraft)
    // SavingsAccount — can never go negative
    public abstract void withdraw(double amount) throws InsufficientFundsException;

    // this.withdraw() ensures that the actual version of the current object is
    // called — not a parent version.
    @Override
    public void transferTo(Account target, double amount) throws InsufficientFundsException {
        this.withdraw(amount);
        target.deposit(amount);

    }

    @Override
    public String toString() {
        return "%s | ID: %s | Balance: %.2f".formatted(this.getClass().getSimpleName(), id, balance);
    }

}

class SavingAccount extends Account {
    private final double rate;

    public SavingAccount(String id, double initial, double rate) {
        super(id, initial);
        if (rate < 0)
            throw new IllegalArgumentException("Rate cannot be negative");
        this.rate = rate;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {

        if (amount < balance)
            throw new InsufficientFundsException(amount, balance);
        balance -= amount;
    }

    public void applyInterest() {
        balance += balance * rate;
    }

}

class CheckingAccount extends Account {
    private final double overdraftLimit;

    public CheckingAccount(String id, double initial, double overdraftLimit) {
        super(id, initial);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance + overdraftLimit) {
            throw new InsufficientFundsException(amount, balance + overdraftLimit);
        }
        balance -= amount;
    }
}

public class BankAccountSystem {

    public static void main(String[] args) throws InsufficientFundsException {
        // Polymorphic collection — all are Accounts
        List<Account> accounts = new ArrayList<Account>();
        var savingAccount = new SavingAccount("SAV-1", 1000, 0.05);
        var checkingAccount = new CheckingAccount("CHK-1", 200, 500);
        accounts.add(savingAccount);
        accounts.add(checkingAccount);

        savingAccount.applyInterest();
        System.out.println(savingAccount.getBalance()); // 1050.0
        savingAccount.transferTo(checkingAccount, 300);
        System.out.println(savingAccount.getBalance());// savings 750, checking 500

        checkingAccount.withdraw(900); // ok: 500 - 900 = -400 (within -500 limit)

        accounts.forEach(account -> System.out.println(account));

        try {
            savingAccount.withdraw(800); // not ok: 750 - 800 = -50 (not allowed for savings)
        } catch (InsufficientFundsException e) {
            System.out.println("Denied. Short by " + e.getShortfall());
        }

    }

}
