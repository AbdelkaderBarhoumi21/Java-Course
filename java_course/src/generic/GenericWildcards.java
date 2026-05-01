package generic;

import java.util.ArrayList;
import java.util.List;

/*
 * Generic wildcards demo.
 *
 * Example 1 — print: List<?> accepts a list of ANY type
 *   List<String> words = List.of("A", "B");
 *   print(words);                         // A B
 *   print(List.of(1, 2, 3));              // 1 2 3
 *
 * Example 2 — sum: List<? extends Number> accepts Number or any subtype
 *                  (read-only — we can read as Number, cannot add)
 *   sum(List.of(1, 2, 3));                // 6.0
 *   sum(List.of(1.5, 2.5));               // 4.0
 *
 * Example 3 — addNumbers: List<? super Integer> accepts Integer or supertype
 *                         (write-friendly — we can add Integer values)
 *   List<Number> nums = new ArrayList<>();
 *   addNumbers(nums);                     // [1, 2]
 *
 * PECS rule: Producer Extends, Consumer Super
 *   - read from the list  → use ? extends T
 *   - write into the list → use ? super T
 */
public class generic_wildcards {

    // ? — any type (read elements as Object)
    public static void print(List<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }

    // ? extends T — T or its subtypes (read-only in practice)
    public static double sum(List<? extends Number> numbers) {
        double total = 0;
        for (Number n : numbers) {
            total += n.doubleValue();
        }
        return total;
    }

    // ? super T — T or its supertypes (writing is allowed)
    public static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
    }

    public static void main(String[] args) {
        // Example 1 — print works with any list type
        List<String> words = List.of("A", "B", "C");
        List<Integer> numbers = List.of(1, 2, 3);
        print(words); // A B C
        print(numbers); // 1 2 3

        // Example 2 — sum works with any subtype of Number
        System.out.println(sum(numbers)); // 6.0
        System.out.println(sum(List.of(1.5, 2.5))); // 4.0

        // Example 3 — addNumbers can write into Integer or supertype lists
        List<Number> nums = new ArrayList<>();
        addNumbers(nums);
        print(nums); // 1 2
    }
}
