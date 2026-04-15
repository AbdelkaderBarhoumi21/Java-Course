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

        // ORDER OF OPERATIONS [P-E-M-D-A-S]
        // P = Parentheses, E = Exponents, M = Multiplication,
        // D = Division, A = Addition, S = Subtraction
        System.out.println("\nOrder of Operations (PEMDAS):");

        double result2 = 3 + 4 * (7 - 5) / 2;
        System.out.println("3 + 4 * (7 - 5) / 2 = " + result2);
        // Step 1: (7 - 5) = 2
        // Step 2: 4 * 2 = 8
        // Step 3: 8 / 2 = 4
        // Step 4: 3 + 4 = 7

        double result3 = (10 + 5) * 2 - 8 / 4;
        System.out.println("(10 + 5) * 2 - 8 / 4 = " + result3);
        // Step 1: (10 + 5) = 15
        // Step 2: 15 * 2 = 30
        // Step 3: 8 / 4 = 2
        // Step 4: 30 - 2 = 28

        double result4 = 100 / (5 * (2 + 3));
        System.out.println("100 / (5 * (2 + 3)) = " + result4);
        // Step 1: (2 + 3) = 5
        // Step 2: 5 * 5 = 25
        // Step 3: 100 / 25 = 4
    }
}
