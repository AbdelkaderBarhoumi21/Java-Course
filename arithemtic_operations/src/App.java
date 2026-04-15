public class App {
    public static void main(String[] args) {
        // Basic Arithmetic Operations
        int a = 20;
        int b = 6;

        // Addition
        int sum = a + b;
        System.out.println("Addition: " + a + " + " + b + " = " + sum);

        // Subtraction
        int difference = a - b;
        System.out.println("Subtraction: " + a + " - " + b + " = " + difference);

        // Multiplication
        int product = a * b;
        System.out.println("Multiplication: " + a + " * " + b + " = " + product);

        // Division
        int quotient = a / b;
        System.out.println("Division: " + a + " / " + b + " = " + quotient);

        // Modulus (remainder)
        int remainder = a % b;
        System.out.println("Modulus: " + a + " % " + b + " = " + remainder);

        // Division with decimals using double
        double x = 20.0;
        double y = 6.0;
        double result = x / y;
        System.out.println("Double Division: " + x + " / " + y + " = " + result);

        // Increment and Decrement
        int counter = 10;
        System.out.println("\nIncrement & Decrement:");
        System.out.println("counter = " + counter);
        counter++;
        System.out.println("counter++ = " + counter);
        counter--;
        System.out.println("counter-- = " + counter);

        // Compound Assignment Operators
        int value = 50;
        System.out.println("\nCompound Assignment Operators:");
        System.out.println("value = " + value);

        value += 10;
        System.out.println("value += 10 → " + value);

        value -= 5;
        System.out.println("value -= 5  → " + value);

        value *= 2;
        System.out.println("value *= 2  → " + value);

        value /= 3;
        System.out.println("value /= 3  → " + value);

        value %= 7;
        System.out.println("value %= 7  → " + value);
    }
}
