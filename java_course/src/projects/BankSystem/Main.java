package projects.BankSystem;

public class Main {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("Abdelkader", 1500.00, 0.05);
        CheckingAccount checking = new CheckingAccount("Barhoumi", 1500.00, 200.00);

        System.out.println("===============Savings account===============");
        System.out.println(savings);
        savings.applyInterest();
        System.out.println(savings);

        System.out.println("===============Checking account===============");
        System.out.println(checking);

        try {
            checking.withdraw(650);// within overdraft limit ✅
            checking.withdraw(200);// exceeds overdraft ❌

        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
