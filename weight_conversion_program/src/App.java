
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Weight Conversion Program");
        System.out.println("1: Convert lbs to kgs");
        System.out.println("2: Convert kgs to lbs");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();

        // option 1 convert lbs to kgs
        switch (option) {
            case 1 -> {
                System.out.print("Enter the weight in lbs: ");
                double lbs = scanner.nextDouble();
                double kgs = lbs * 0.453592;
                System.out.printf("The new weight in kgs is: %.2f%n", kgs);
            }
            case 2 -> {
                System.out.print("Enter the weight in kgs: ");
                double kgs = scanner.nextDouble();
                double lbs = kgs * 2.20462;
                System.out.printf("The new weight in lbs is: %.2f%n", lbs);
            }
            default ->
                System.out.println("Not a valid choice");
        }

        scanner.close();
    }
}
