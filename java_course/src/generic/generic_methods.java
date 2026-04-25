package generic;

/*
 * Generic methods demo.
 *
 * Example 1 — printArray: works with any array type (Integer, String, ...)
 *   Integer[] numbers = {1, 2, 3};
 *   Utils.printArray(numbers);            // 1 2 3
 *   Utils.printArray(new String[]{"A","B","C"}); // A B C
 *
 * Example 2 — max: returns the greater of two values, T must be Comparable
 *   Utils.max(10, 20);                    // 20
 *   Utils.max("Apple", "Banana");         // Banana
 */
public class generic_methods {
    public static void main(String[] args) {

        Integer[] numbers = { 1, 2, 3 };
        String[] words = { "A", "B", "C" };

        // Example 1 — generic method without constraint
        Utils.printArray(numbers); // 1 2 3
        Utils.printArray(words); // A B C

        // Example 2 — generic method with Comparable constraint
        System.out.println(Utils.max(10, 20)); // 20
        System.out.println(Utils.max("Apple", "Banana")); // Banana
    }

}

class Utils {
    // <T> declares the generic type BEFORE the return type
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // With constraint — T must implement Comparable
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }
}