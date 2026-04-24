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
        // Set.of(...) -> creates an IMMUTABLE Set in one line (Java 9+).
        // Cannot be modified afterwards (add/remove would throw at runtime),
        // and rejects duplicates or nulls at creation time.
        Set<String> otherLanguages = Set.of("Java", "Go", "Rust");

        // Why "new HashSet<>(languages)"?
        // It creates a COPY of 'languages' (a separate object with the same elements).
        // We copy because addAll/retainAll/removeAll MUTATE the set they're called on.
        // Without the copy, the first operation would destroy the original 'languages'.

        // Union -> all elements from both sets (A ∪ B)
        Set<String> union = new HashSet<>(languages); // copy of languages
        union.addAll(otherLanguages);                 // add every element from otherLanguages (duplicates ignored)
        System.out.println("Union: " + union);

        // Intersection -> only elements present in BOTH sets (A ∩ B)
        Set<String> intersection = new HashSet<>(languages); // copy of languages
        intersection.retainAll(otherLanguages);              // keep only those also in otherLanguages
        System.out.println("Intersection: " + intersection); // {Java}

        // Difference -> elements in the first set but NOT in the second (A \ B)
        Set<String> difference = new HashSet<>(languages); // copy of languages
        difference.removeAll(otherLanguages);              // remove every element also in otherLanguages
        System.out.println("Difference: " + difference);   // {Python}



    }
}
