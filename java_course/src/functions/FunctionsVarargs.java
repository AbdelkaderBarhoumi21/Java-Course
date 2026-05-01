package functions;

/**
 * Varargs in Java:
 * - "int... num" accepts zero or more arguments.
 * - Internally, it is treated as an int[] array.
 * - A method can only have one varargs parameter, and it must be the last parameter.
 */
public class functions_varargs {

    public static int somme(int... num) {
        int total = 0;
        for (int n : num) {
            total += n;
        }

        return total;
    }

    public static void main(String[] args) {
        int result = somme(1, 2, 3, 4, 5);
        System.out.println("La somme est: " + result);

        // Usage examples
        System.out.println(somme(1, 2));          // 3
        System.out.println(somme(1, 2, 3, 4, 5)); // 15
        System.out.println(somme());              // 0 (no arguments)
    }
}
