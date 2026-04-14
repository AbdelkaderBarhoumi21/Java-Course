import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        // --- Primitive types ---

        // int: whole numbers
        int age = 25;
        int count = -10;

        // double: decimal numbers
        double price = 9.99;
        double temperature = -3.5;

        // float: decimal numbers (less precision, needs 'f')
        float height = 1.75f;

        // long: large whole numbers (needs 'L')
        long population = 8000000000L;

        // short: small whole numbers (-32768 to 32767)
        short year = 2026;

        // byte: very small numbers (-128 to 127)
        byte level = 5;

        // char: single character (uses single quotes)
        char grade = 'A';
        char symbol = '#';

        // boolean: true or false
        boolean isActive = true;
        boolean hasPermission = false;

        // --- Reference types ---

        // String: text (uses double quotes)
        String name = "Abdel";
        String message = "Hello World";

        // Array: collection of values
        boolean[] flags = {true, false, false};
        int[] numbers = {1, 2, 3, 4, 5};
        String[] colors = {"red", "green", "blue"};

        // --- Copying behavior ---

        // Primitive: copies the VALUE (independent)
        int a = 5;
        int b = a;
        b = 10;
        System.out.println("a = " + a); // 5 — not affected
        System.out.println("b = " + b); // 10

        // Reference: copies the ADDRESS (same object)
        String x = "Hello";
        String y = x;
        System.out.println("x = " + x); // Hello
        System.out.println("y = " + y); // Hello

        // --- Print all variables ---
        System.out.println("--- Primitive types ---");
        System.out.println("int age = " + age);
        System.out.println("int count = " + count);
        System.out.println("double price = " + price);
        System.out.println("double temperature = " + temperature);
        System.out.println("float height = " + height);
        System.out.println("long population = " + population);
        System.out.println("short year = " + year);
        System.out.println("byte level = " + level);
        System.out.println("char grade = " + grade);
        System.out.println("char symbol = " + symbol);
        System.out.println("boolean isActive = " + isActive);
        System.out.println("boolean hasPermission = " + hasPermission);
        System.out.println("--- Reference types ---");
        System.out.println("String name = " + name);
        System.out.println("String message = " + message);
        /// Arrays.toString() — converts an array to a readable string
        /// java.util   → the package (folder) where the class lives
        /// Arrays       → a utility class with helper methods for arrays
        /// toString()   → the method that converts the array to a readable string
        /// flags        → your array being converted
        System.out.println("boolean[] flags = " + Arrays.toString(flags));
        System.out.println("int[] numbers = " + Arrays.toString(numbers));
        System.out.println("String[] colors = " + Arrays.toString(colors));
    }
}
