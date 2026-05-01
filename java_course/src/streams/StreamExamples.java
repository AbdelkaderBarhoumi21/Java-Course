package streams;

import java.util.List;
import java.util.stream.Collectors;

public class stream_examples {
    public static void main(String[] args) {
        List<String> noms = List.of("Alice", "Bob", "Charlie", "Anna", "Ahmed", "Bella");

        // 1. Filter — keep names that start with "A"

        List<String> nomsEnA = noms.stream().filter(n -> n.startsWith("A")).toList();
        System.out.println(nomsEnA);

        // 2. Transform — convert to uppercase

        List<String> majuscules = noms.stream().map(String::toUpperCase).toList();
        System.out.println(majuscules);

        // 3. Sort

        List<String> trier = noms.stream().sorted().toList();
        System.out.println(trier);

        // 4. Combine multiple operations

        List<String> result = noms.stream().filter(n -> n.length() > 3)
                .map(String::toUpperCase).sorted().toList();
        System.out.println(result);

        // 5. Count
        long count = noms.stream().filter(n -> n.startsWith("A")).count();
        System.out.println(count);

        // 6. Concatenate — join all elements with a separator
        // Collectors.joining(", ") creates a string by inserting ", " between each element of the stream.
        // This is a terminal operation that reduces the stream into a single String.
        // You can also use joining() without a separator, or joining(", ", "[", "]") to add a prefix and suffix.
        String concatene = noms.stream()
                .collect(Collectors.joining(", "));
        System.out.println(concatene); // "Alice, Bob, Charlie, Anna, Ahmed, Bella"

    }

}
