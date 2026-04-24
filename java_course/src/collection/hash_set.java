package collection;

import java.util.HashSet;
import java.util.Set;

/**
 * HashSet: an unordered collection of UNIQUE elements (no duplicates).
 *
 * - add() returns false and ignores the element if it's already there.
 * - Fast O(1) average-time for add / remove / contains.
 * - No guaranteed order (use LinkedHashSet for insertion order, TreeSet for sorted).
 *
 * Typical use cases: membership tests, deduplication, and set operations
 * (union, intersection, difference).
 */
public class hash_set {
    public static void main(String[] args) {
        Set<String> languages = new HashSet<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("Java"); // Ignored - no duplicates

        System.out.println(languages.size());              // 2
        System.out.println(languages.contains("Java"));    // true

        // Set operations
        Set<String> otherLanguages = Set.of("Java", "Go", "Rust");

        // Union -> all elements from both sets
        Set<String> union = new HashSet<>(languages);
        union.addAll(otherLanguages);
        System.out.println("Union: " + union);

        // Intersection -> only elements present in BOTH sets
        Set<String> intersection = new HashSet<>(languages);
        intersection.retainAll(otherLanguages);
        System.out.println("Intersection: " + intersection); // {Java}

        // Difference -> elements in the first set but NOT in the second
        Set<String> difference = new HashSet<>(languages);
        difference.removeAll(otherLanguages);
        System.out.println("Difference: " + difference);     // {Python}
    }
}
