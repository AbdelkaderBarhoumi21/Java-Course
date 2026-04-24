package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap: a key-value store from java.util.
 * Unordered, no duplicate keys (a second put on the same key replaces it),
 * and O(1) average-time for get/put/remove.
 *
 * Common operations: put, get, getOrDefault, containsKey, containsValue,
 * size, remove, and iteration via entrySet / keySet / values.
 * Advanced: putIfAbsent, merge, compute.
 */
public class hash_map {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();

        // Add key-value pairs
        scores.put("Alice", 95);
        scores.put("Bob", 82);
        scores.put("Charlie", 90);
        scores.put("Alice", 90); // Replaces the previous value!
        System.out.println(scores);

        // Access the value associated with a key
        int aliceScore = scores.get("Alice");
        System.out.println("Alice's score: " + aliceScore);

        // getOrDefault -> returns the default if the key is absent
        int defaultScore = scores.getOrDefault("David", 0);
        System.out.println("David's score: " + defaultScore);

        // Check if a key exists
        boolean containsAlice = scores.containsKey("Alice");
        System.out.println("Contains Alice: " + containsAlice);

        // Check if a value exists
        boolean containsScore = scores.containsValue(90);
        System.out.println("Contains 90: " + containsScore);

        // size
        int size = scores.size();
        System.out.println("Size: " + size);

        // Remove a key-value pair
        scores.remove("Charlie");
        System.out.println(scores);

        // Iterate over entries (each element is a Map.Entry = one key-value pair)
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Iterate over keys / values only
        scores.keySet().forEach(System.out::println);
        scores.values().forEach(System.out::println);

        // Advanced features
        scores.putIfAbsent("David", 62); // Adds only if the key is absent
        scores.merge("Alice", 5, Integer::sum); // If Alice exists, combine old + 5 by adding them
        scores.compute("Bob", (k, v) -> v + 10); // Bob -> 82 + 10 = 92

    }

}
