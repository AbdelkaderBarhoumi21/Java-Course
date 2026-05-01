package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates the difference between map() and flatMap().
 *
 * - map()      : transforms each element 1 -> 1 (keeps nested structure).
 * - flatMap()  : transforms each element 1 -> N then flattens all sub-streams
 *                into a single flat stream.
 * - distinct() : removes duplicates from the stream using equals(), preserving
 *                the first-occurrence order. Works automatically on records;
 *                regular classes must override equals() and hashCode().
 *
 * Rule: use flatMap() when each element produces a collection/array/stream
 * and you want to merge them into one flat result.
 */
public class stream_flatmap {
    public static void main(String[] args) {

        // -----------------------------------------------------------
        // Example 1 : List of lists (students and their courses)
        // -----------------------------------------------------------
        List<List<String>> coursParEtudiant = List.of(
                List.of("Java", "Python"),
                List.of("C++", "Java"),
                List.of("SQL"));

        // map() -> keeps nested structure (List of Lists)
        List<List<String>> withMap = coursParEtudiant.stream()
                .map(list -> list) // 1 list -> 1 list
                .toList();
        System.out.println("map()     : " + withMap);
        // [[Java, Python], [C++, Java], [SQL]]

        // flatMap() -> flattens into a single list
        List<String> withFlatMap = coursParEtudiant.stream()
                .flatMap(List::stream) // 1 list -> N elements
                .toList();
        System.out.println("flatMap() : " + withFlatMap);
        // [Java, Python, C++, Java, SQL]

        // -----------------------------------------------------------
        // Example 2 : Sentences split into words
        // -----------------------------------------------------------
        List<String> phrases = List.of(
                "Bonjour le monde",
                "Java est puissant",
                "Streams sont utiles");

        // map() -> Stream of String[] (still nested)
        List<String[]> motsImbriques = phrases.stream()
                .map(p -> p.split(" "))
                .toList();
        System.out.println("map()     : " + motsImbriques.size() + " arrays");
        // 3 arrays (not flat)

        // flatMap() -> single flat stream of all words, no duplicates
        
        List<String> motsUniques = phrases.stream()
                .flatMap(p -> Arrays.stream(p.split(" ")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("flatMap() : " + motsUniques);
        // [Bonjour, le, monde, Java, est, puissant, Streams, sont, utiles]
    }

}
