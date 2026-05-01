package optional;

import java.util.Optional;

/**
 * Optional<T> examples: a container that holds a value or is empty,
 * used to make absence explicit and avoid NullPointerException.
 * Demonstrates creation, safe extraction, and functional chaining.
 */
public class OptionalExamples {
    public static void main(String[] args) {

        // Create an Optional that holds a non-null value
        Optional<String> hasValue = Optional.of("has a value");
        // Create an empty Optional (no value inside)
        Optional<String> isEmptyValue = Optional.empty();
        // Wrap a possibly-null reference: becomes empty if the argument is null
        Optional<String> nullable = Optional.ofNullable(null);

        // Old style: check presence first, then extract â€” works but verbose
        if (hasValue.isPresent()) {
            // get() returns the inner value; throws NoSuchElementException if empty
            System.out.println(hasValue.get());
        }

        // Recommended: ifPresent runs the action only when a value exists
        hasValue.ifPresent(v -> System.out.println("Recommanded way" + v));
        // ifPresentOrElse (Java 9+): one branch when present, another when empty
        isEmptyValue.ifPresentOrElse(v -> System.out.println(v), () -> System.out.println("is empty value"));
        // Same as above but using a method reference instead of a lambda
        hasValue.ifPresent(System.out::println);// per refrenece

        // Chain filter + map: keep value only if length > 3, then uppercase it
        Optional<String> filter = hasValue.filter(v -> v.length() > 3).map(String::toUpperCase);
        // Print the result only if the chain produced a value
        filter.ifPresent(System.out::println);

        // orElseThrow: return the value if present, otherwise throw the supplied exception
        String withExcpetion = nullable.orElseThrow(() -> new RuntimeException("No value"));
        // Print the extracted value (only reached if no exception was thrown)
        System.out.println(withExcpetion);

        // Functional style: transform if present, fall back to a default if empty
        String result = isEmptyValue.map(String::toUpperCase).orElse("Default value");
        System.out.println(result);

    }

}
