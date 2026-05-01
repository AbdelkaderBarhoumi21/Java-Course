package projects;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double amount) {
        super("Insufficient funds. Tried to withdraw: " + amount);
    }
}



public class BankAccountSystem {
    public static void main(String[] args) {
    }

}
