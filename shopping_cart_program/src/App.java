
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Shopping cart program 
        Scanner  scanner = new Scanner(System.in);

        String item;
        double price;
        int quantity;
        char currency ='$';
        double total;

        System.out.print("What item wouloud you like to buy?: ");
        item=scanner.nextLine();
        System.out.print("What is the price of the item?: ");
        price=scanner.nextDouble();
        System.out.print("How many would you like to buy?: ");
        quantity=scanner.nextInt();

        total =price * quantity;
        System.out.println("Total cost: " + currency + total);


}}
