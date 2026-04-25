package functions;

/*
 * Functional Interface — an interface with EXACTLY ONE abstract method.
 *
 * The @FunctionalInterface annotation tells the compiler to enforce this rule
 * (adding a second abstract method would cause a compile error).
 *
 * A functional interface can be implemented with a lambda expression
 * instead of writing a full class — which makes the code shorter and clearer.
 *
 * Example — Calculator has one method: calculate(a, b)
 *
 *   Calculator add      = (a, b) -> a + b;
 *   Calculator multiply = (a, b) -> a * b;
 *
 *   add.calculate(2, 3);       // 5.0
 *   multiply.calculate(2, 3);  // 6.0
 */
public class interface_function {
    public static void main(String[] args) {

        // Each lambda is an implementation of the Calculator interface
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> a / b;

        System.out.println("2 + 3 = " + add.calculate(2, 3)); // 5.0
        System.out.println("5 - 2 = " + subtract.calculate(5, 2)); // 3.0
        System.out.println("2 * 3 = " + multiply.calculate(2, 3)); // 6.0
        System.out.println("10 / 4 = " + divide.calculate(10, 4)); // 2.5
    }
}

@FunctionalInterface
interface Calculator {
    double calculate(double a, double b);}

    

    
    

    

    
    
        