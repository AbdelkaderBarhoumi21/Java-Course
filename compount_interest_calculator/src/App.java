import java.util.Scanner;public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner= new Scanner(System.in);
        double principal;
        double rate;
        int timesComounded;
        int years;
        double amount ;

        System.out.print("Enter the principal amount: ");
        principal= scanner.nextDouble();
        System.out.print("Enter the interest rate (in %): ");
        rate=scanner.nextDouble() / 100;
        System.out.print("Enter the number of times interest is compounded per year: ");
        timesComounded=scanner.nextInt();
        System.out.print("Enter the number of years: ");
        years=scanner.nextInt();

        amount =principal * Math.pow(1 + rate / timesComounded, timesComounded * years);

        System.out.printf("The amount after %d years is $%.2f",years,amount);
        scanner.close();
    }
}
