import Discount.FixedDiscount;
import Discount.PercentageDiscount;
import Domain.Order;
import Domain.Product;
import Repository.*;
import Services.*;

public class OrderSystem {

    static public void main(String[] args) {

        // Catalogue
        var apple = new Product("P001", "Apple", 0.50, 1, 100);
        var laptop = new Product("P002", "Laptop", 999.99, 1, 5);
        var book = new Product("P003", "Redmi", 34.90, 1, 20);

        // DIP
        var repo = new FileOrderRepository("orders.json");
        var cart = new CartService();
        var orderService = new OrderService(repo);

        System.out.println("\n════════════════════════════════════");
        System.out.println("       ORDER SYSTEM — Java          ");
        System.out.println("════════════════════════════════════");

        System.out.println("\n▶ Adding products to cart...");
        cart.addProduct(apple, 6);
        cart.addProduct(laptop, 1);
        cart.addProduct(book, 2);
        cart.printSummary();

        // --- Increment / Decrement ---
        System.out.println("\n▶ Incrementing Apple qty...");
        cart.increment("P001");
        System.out.println("\n▶ Decrementing Laptop qty (min=1)...");
        cart.decrement("P002"); // should warn — already at min
        cart.printSummary();

        // --- Remove ---
        System.out.println("\n▶ Removing Book from cart...");
        cart.removeItem("P003");
        cart.printSummary();

        // --- Discount ---
        System.out.println("\n▶ Applying 10% discount...");
        cart.applyDiscount(new PercentageDiscount(10));
        cart.printSummary();

        // --- Second order ---
        System.out.println("\n▶ New order with fixed discount...");
        cart.addProduct(book, 3);
        cart.applyDiscount(new FixedDiscount(5.00));
        cart.printSummary();
        Order order2 = orderService.placeOrder(cart);
        System.out.println("  ✓ Order placed: " + order2.id());
        System.out.println("\n════════════════════════════════════");
        System.out.println("  All orders saved to orders.json   ");
        System.out.println("════════════════════════════════════\n");

    }

}
