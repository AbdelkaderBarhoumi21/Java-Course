package functions;

/**
 * Functions in Java:
 * - Overloading lets you define several methods with the same name but different parameters.
 * - "static" means the method belongs to the class, not to an instance —
 *   you can call it directly via ClassName.method().
 * - "return" sends back a value and ends the method.
 */
public class functions {

    // Method that returns a value
    public static int additionner(int a, int b) {
        return a + b;
    }

    // Method that returns nothing (void)
    public static void afficherMessage(String message) {
        System.out.println(">>> " + message);
    }

    // Method with a simulated default value (overloading)
    public static double calculerTVA(double prix) {
        return calculerTVA(prix, 0.20); // default VAT: 20%
    }

    public static double calculerTVA(double prix, double taux) {
        return prix * (1 + taux);
    }

    public static void main(String[] args) {
        int result = additionner(5, 2);
        System.out.println("Addition result: " + result);

        afficherMessage("Hello");

        double prixAvecTVA = calculerTVA(100);
        System.out.println("Price with default VAT: " + prixAvecTVA);

        double prixAvecTVA15 = calculerTVA(100, 0.15);
        System.out.println("Price with 15% VAT: " + prixAvecTVA15);
    }

}
