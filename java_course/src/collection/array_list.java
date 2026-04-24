package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ArrayList: a resizable array from java.util.
 * Ordered, allows duplicates, fast random access via get(index).
 *
 * Common operations: add, get, set, size, contains, indexOf, remove, sort,
 * and iteration with for-each or forEach().
 */
public class array_list {
    public static void main(String[] args) {

        // Declare as List (interface), instantiate as ArrayList (implementation)
        List<String> cities = new ArrayList<>();
        cities.add("Tunisia");
        cities.add("Algeria");
        cities.add("Libya");
        cities.add("Morocco");
        cities.add("Mauritania");

        // get(index) -> read element at a given position
        String firstItem = cities.get(0);
        System.out.println(firstItem);

        // set(index, value) -> replace element at a given position
        cities.set(1, "Comoros");
        System.out.println(cities.get(1));

        // size() -> number of elements
        int size = cities.size();
        System.out.println(size);

        // contains(value) -> true if the value is in the list
        boolean contains = cities.contains("Tunisia");
        System.out.printf("Contains: %b%n", contains);

        // indexOf(value) -> position of the first match, or -1 if absent
        int firstItemIndex = cities.indexOf("Tunisia");
        System.out.println(firstItemIndex);

        // remove(int) removes by INDEX, remove(Object) removes by VALUE
        cities.remove(4);            // remove last element by index
        cities.remove("Tunisia");    // remove by value
        System.out.println(cities);

        // Collections (plural) is the utility class with sort/reverse/shuffle...
        Collections.sort(cities);
        System.out.println(cities);

        // Iterate with an enhanced for-loop
        for (String city : cities) {
            System.out.println(city);
        }

        // Iterate with forEach + lambda
        cities.forEach(c -> System.out.println(c));

    }

}
