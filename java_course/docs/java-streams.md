# Java Streams Comprehensive Guide

Java Streams, introduced in **Java 8**, represent a powerful abstraction for processing sequences of elements in a functional, declarative style. Streams enable you to express complex data processing queries with concise and readable code.

---

## Table of Contents

1. [What is a Stream?](#what-is-a-stream)
2. [Stream vs Collection](#stream-vs-collection)
3. [Creating Streams](#creating-streams)
4. [Intermediate Operations](#intermediate-operations)
5. [Terminal Operations](#terminal-operations)
6. [Collectors](#collectors)
7. [Primitive Streams](#primitive-streams)
8. [Parallel Streams](#parallel-streams)
9. [Common Patterns](#common-patterns)
10. [Best Practices](#best-practices)

---

## What is a Stream?

A **Stream** is a sequence of elements supporting sequential and parallel aggregate operations. Think of it as a pipeline through which data flows, undergoing various transformations until reaching a final result.

### Key Characteristics

| Characteristic | Description |
|----------------|-------------|
| **No Storage** | Streams don't store elements; they compute them on demand |
| **Functional** | Operations don't mutate the source; they produce new streams |
| **Lazy Evaluation** | Intermediate operations are only executed when a terminal operation is invoked |
| **Possibly Infinite** | Streams can be infinite (e.g., `Stream.iterate`) |
| **Consumable** | A stream can be traversed only once |

```java
import java.util.List;
import java.util.stream.Stream;

public class StreamBasics {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        // Stream pipeline example
        long count = names.stream()
                .filter(name -> name.length() > 3)   // Intermediate
                .map(String::toUpperCase)             // Intermediate
                .count();                             // Terminal

        System.out.println("Count: " + count); // Output: 4
    }
}
```

---

## Stream vs Collection

| Aspect | Collection | Stream |
|--------|-----------|--------|
| **Purpose** | Store and access data | Compute data |
| **Iteration** | External (you control it) | Internal (library handles it) |
| **Reusability** | Can be traversed multiple times | Consumed after one use |
| **Size** | Finite | Can be finite or infinite |
| **Modification** | Can add/remove elements | Immutable pipeline |

```java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamVsCollection {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>(List.of("Apple", "Banana", "Cherry"));

        // Collection: can be iterated multiple times
        for (String fruit : fruits) System.out.println(fruit);
        for (String fruit : fruits) System.out.println(fruit);

        // Stream: consumed after one use
        Stream<String> stream = fruits.stream();
        stream.forEach(System.out::println);
        // stream.forEach(System.out::println); // IllegalStateException!
    }
}
```

---

## Creating Streams

### From Collections

```java
import java.util.*;

public class CreatingStreams {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Set<String> set = new HashSet<>(List.of("a", "b", "c"));

        list.stream();  // From List
        set.stream();   // From Set
    }
}
```

### From Arrays

```java
import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayStreams {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        String[] words = {"Hello", "World"};

        Arrays.stream(numbers);        // IntStream
        Arrays.stream(words);          // Stream<String>
        Stream.of("a", "b", "c");      // Stream<String>
    }
}
```

### From Values and Empty Streams

```java
import java.util.stream.Stream;

public class ValueStreams {
    public static void main(String[] args) {
        Stream<String> single = Stream.of("Java");
        Stream<String> empty = Stream.empty();
        Stream<String> nullable = Stream.ofNullable(getString()); // Java 9+
    }

    static String getString() {
        return null;
    }
}
```

### Infinite Streams

```java
import java.util.stream.Stream;

public class InfiniteStreams {
    public static void main(String[] args) {
        // Generate constant values
        Stream.generate(() -> "Hello")
              .limit(5)
              .forEach(System.out::println);

        // Iterate with seed and function
        Stream.iterate(0, n -> n + 2)
              .limit(10)
              .forEach(System.out::println); // Even numbers

        // Java 9+ iterate with predicate (finite)
        Stream.iterate(0, n -> n < 100, n -> n + 1)
              .forEach(System.out::println);
    }
}
```

### From I/O and Files

```java
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;

public class FileStreams {
    public static void main(String[] args) throws IOException {
        // Stream lines from a file
        try (Stream<String> lines = Files.lines(Path.of("data.txt"))) {
            lines.filter(line -> line.contains("ERROR"))
                 .forEach(System.out::println);
        }

        // Stream directory entries
        try (Stream<Path> paths = Files.list(Path.of("."))) {
            paths.filter(Files::isRegularFile)
                 .forEach(System.out::println);
        }

        // Walk directory tree
        try (Stream<Path> walk = Files.walk(Path.of("."), 2)) {
            walk.filter(Files::isDirectory)
                .forEach(System.out::println);
        }
    }
}
```

### Building Streams

```java
import java.util.stream.Stream;

public class BuildingStreams {
    public static void main(String[] args) {
        Stream.Builder<String> builder = Stream.builder();
        builder.add("First")
               .add("Second")
               .add("Third");
        Stream<String> stream = builder.build();
    }
}
```

---

## Intermediate Operations

Intermediate operations return a new stream and are **lazy** — they don't execute until a terminal operation is called.

### Filtering

```java
import java.util.List;

public class Filtering {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter: keep elements matching predicate
        List<Integer> even = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList(); // [2, 4, 6, 8, 10]

        // distinct: remove duplicates
        List<Integer> unique = List.of(1, 2, 2, 3, 3, 3).stream()
                .distinct()
                .toList(); // [1, 2, 3]

        // dropWhile (Java 9+): drop elements while predicate is true
        List<Integer> dropped = numbers.stream()
                .dropWhile(n -> n < 5)
                .toList(); // [5, 6, 7, 8, 9, 10]

        // takeWhile (Java 9+): take elements while predicate is true
        List<Integer> taken = numbers.stream()
                .takeWhile(n -> n < 5)
                .toList(); // [1, 2, 3, 4]
    }
}
```

### Mapping

```java
import java.util.List;

public class Mapping {
    public static void main(String[] args) {
        List<String> names = List.of("alice", "bob", "charlie");

        // map: transform each element
        List<Integer> lengths = names.stream()
                .map(String::length)
                .toList(); // [5, 3, 7]

        // flatMap: flatten nested structures
        List<List<Integer>> nested = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5, 6)
        );

        List<Integer> flat = nested.stream()
                .flatMap(List::stream)
                .toList(); // [1, 2, 3, 4, 5, 6]

        // flatMap with words
        List<String> phrases = List.of("Hello World", "Java Streams");
        List<String> words = phrases.stream()
                .flatMap(phrase -> Stream.of(phrase.split(" ")))
                .toList(); // ["Hello", "World", "Java", "Streams"]
    }
}
```

### Sorting and Ordering

```java
import java.util.*;
import java.util.stream.Stream;

public class Sorting {
    public static void main(String[] args) {
        List<String> fruits = List.of("Banana", "Apple", "Cherry", "Date");

        // sorted: natural order
        List<String> sorted = fruits.stream()
                .sorted()
                .toList(); // [Apple, Banana, Cherry, Date]

        // sorted: custom comparator
        List<String> byLength = fruits.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList(); // [Date, Apple, Banana, Cherry]

        // sorted: reversed
        List<String> reversed = fruits.stream()
                .sorted(Comparator.reverseOrder())
                .toList(); // [Date, Cherry, Banana, Apple]

        // unordered: hint for parallel optimization
        Stream<String> unordered = fruits.stream().unordered();
    }
}
```

### Peeking (Debugging)

```java
import java.util.List;

public class Peeking {
    public static void main(String[] args) {
        List<Integer> result = List.of(1, 2, 3, 4, 5).stream()
                .peek(n -> System.out.println("Before filter: " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("After filter: " + n))
                .map(n -> n * 10)
                .peek(n -> System.out.println("After map: " + n))
                .toList();
    }
}
```

### Limiting and Skipping

```java
import java.util.List;
import java.util.stream.Stream;

public class LimitSkip {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // skip first N elements
        List<Integer> skipped = numbers.stream()
                .skip(5)
                .toList(); // [6, 7, 8, 9, 10]

        // limit to N elements
        List<Integer> limited = numbers.stream()
                .limit(3)
                .toList(); // [1, 2, 3]

        // pagination pattern
        List<Integer> page2 = numbers.stream()
                .skip(5)    // skip page 1 (items 0-4)
                .limit(5)   // take page 2 (items 5-9)
                .toList();  // [6, 7, 8, 9, 10]
    }
}
```

---

## Terminal Operations

Terminal operations trigger the actual computation and produce a result or side effect.

### Matching

```java
import java.util.List;

public class Matching {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(2, 4, 6, 8, 10);

        // anyMatch: at least one matches
        boolean hasOdd = numbers.stream().anyMatch(n -> n % 2 != 0); // false

        // allMatch: all match
        boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0); // true

        // noneMatch: none match
        boolean noNegative = numbers.stream().noneMatch(n -> n < 0); // true
    }
}
```

### Finding

```java
import java.util.List;
import java.util.Optional;

public class Finding {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie");

        // findFirst: first element (deterministic)
        Optional<String> first = names.stream()
                .filter(name -> name.startsWith("A"))
                .findFirst(); // Optional[Alice]

        // findAny: any element (better for parallel)
        Optional<String> any = names.stream()
                .filter(name -> name.length() > 3)
                .findAny(); // Optional[Alice] or Optional[Charlie]
    }
}
```

### Aggregation

```java
import java.util.List;
import java.util.Optional;

public class Aggregation {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6);

        // count
        long count = numbers.stream().count(); // 8

        // min/max
        Optional<Integer> min = numbers.stream().min(Integer::compareTo); // Optional[1]
        Optional<Integer> max = numbers.stream().max(Integer::compareTo); // Optional[9]

        // reduce: combine elements
        int sum = numbers.stream().reduce(0, Integer::sum); // 31

        // reduce without identity (returns Optional)
        Optional<Integer> product = numbers.stream()
                .reduce((a, b) -> a * b); // Optional[6480]

        // reduce: custom operation
        String joined = List.of("a", "b", "c").stream()
                .reduce("", (a, b) -> a + "," + b); // ",a,b,c"
    }
}
```

### Iteration and Consumption

```java
import java.util.List;

public class Iteration {
    public static void main(String[] args) {
        List<String> items = List.of("A", "B", "C");

        // forEach: perform action on each element
        items.stream().forEach(System.out::println);

        // forEachOrdered: maintains encounter order (important for parallel)
        items.parallelStream().forEachOrdered(System.out::println);
    }
}
```

### Collecting

```java
import java.util.*;
import java.util.stream.Collectors;

public class Collecting {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        // toList (Java 16+)
        List<String> list = names.stream().toList();

        // toSet
        Set<String> set = names.stream().collect(Collectors.toSet());

        // toCollection: specific collection type
        LinkedList<String> linkedList = names.stream()
                .collect(Collectors.toCollection(LinkedList::new));

        // toArray
        String[] array = names.stream().toArray(String[]::new);
    }
}
```

---

## Collectors

The `Collectors` utility class provides numerous built-in collectors for common operations.

### Joining Strings

```java
import java.util.List;
import java.util.stream.Collectors;

public class Joining {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "is", "awesome");

        String joined = words.stream()
                .collect(Collectors.joining(" ")); // "Java is awesome"

        String withPrefixSuffix = words.stream()
                .collect(Collectors.joining(", ", "[", "]")); // "[Java, is, awesome]"
    }
}
```

### Grouping

```java
import java.util.*;
import java.util.stream.Collectors;

public class Grouping {
    record Person(String name, String department, int salary) {}

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Alice", "Engineering", 80000),
                new Person("Bob", "Sales", 60000),
                new Person("Charlie", "Engineering", 90000),
                new Person("David", "Sales", 65000)
        );

        // group by department
        Map<String, List<Person>> byDept = people.stream()
                .collect(Collectors.groupingBy(Person::department));

        // group by with counting
        Map<String, Long> countByDept = people.stream()
                .collect(Collectors.groupingBy(
                        Person::department,
                        Collectors.counting()
                ));

        // group by with averaging
        Map<String, Double> avgSalary = people.stream()
                .collect(Collectors.groupingBy(
                        Person::department,
                        Collectors.averagingInt(Person::salary)
                ));

        // partitioning (special case of grouping)
        Map<Boolean, List<Person>> highEarners = people.stream()
                .collect(Collectors.partitioningBy(p -> p.salary > 70000));
    }
}
```

### Mapping and Reducing within Collectors

```java
import java.util.*;
import java.util.stream.Collectors;

public class MappingCollectors {
    record Product(String name, String category, double price) {}

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Phone", "Electronics", 800.0),
                new Product("Shirt", "Clothing", 50.0)
        );

        // mapping within grouping
        Map<String, List<String>> namesByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.mapping(Product::name, Collectors.toList())
                ));

        // summing
        double totalPrice = products.stream()
                .collect(Collectors.summingDouble(Product::price)); // 2050.0

        // averaging
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(Product::price)); // 683.33...

        // summarizing (count, sum, min, average, max)
        DoubleSummaryStatistics stats = products.stream()
                .collect(Collectors.summarizingDouble(Product::price));
        System.out.println(stats.getAverage());
        System.out.println(stats.getMax());
    }
}
```

### Custom Collectors

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class CustomCollector {
    public static void main(String[] args) {
        List<String> words = List.of("apple", "banana", "cherry");

        // Custom collector: collect to an immutable sorted set
        Collector<String, ?, Set<String>> toImmutableSortedSet =
                Collector.of(
                        TreeSet::new,           // supplier
                        Set::add,                // accumulator
                        (left, right) -> {       // combiner
                            left.addAll(right);
                            return left;
                        },
                        Collections::unmodifiableSet // finisher
                );

        Set<String> result = words.stream().collect(toImmutableSortedSet);
    }
}
```

---

## Primitive Streams

Java provides specialized streams for primitives to avoid boxing overhead: `IntStream`, `LongStream`, and `DoubleStream`.

```java
import java.util.List;
import java.util.stream.*;

public class PrimitiveStreams {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // mapToInt: Stream<Integer> -> IntStream
        IntStream intStream = numbers.stream().mapToInt(Integer::intValue);

        // Creating primitive streams directly
        IntStream.range(1, 5);      // [1, 2, 3, 4]
        IntStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]

        // Common primitive operations
        int sum = IntStream.range(1, 6).sum();           // 15
        double average = IntStream.range(1, 6).average().orElse(0); // 3.0
        int max = IntStream.range(1, 6).max().orElse(0);  // 5
        long count = IntStream.range(1, 6).count();       // 5

        // Boxing and unboxing
        Stream<Integer> boxed = IntStream.range(1, 5).boxed(); // IntStream -> Stream<Integer>
        IntStream unboxed = Stream.of(1, 2, 3).mapToInt(Integer::intValue);

        // DoubleStream example
        double[] doubles = {1.5, 2.5, 3.5};
        double sumDoubles = Arrays.stream(doubles).sum(); // 7.5
    }
}
```

### Specialized Operations

```java
import java.util.stream.IntStream;

public class SpecializedOperations {
    public static void main(String[] args) {
        // generate random numbers
        IntStream randomInts = new java.util.Random().ints(5, 1, 100);

        // iterate (like Stream.iterate but for primitives)
        IntStream.iterate(0, n -> n + 2).limit(10); // even numbers

        // generate
        IntStream.generate(() -> 42).limit(5);

        // concat
        IntStream.concat(IntStream.of(1, 2), IntStream.of(3, 4));
    }
}
```

---

## Parallel Streams

Parallel streams leverage multi-core processors by splitting work across multiple threads.

```java
import java.util.List;

public class ParallelStreams {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Get parallel stream
        numbers.parallelStream()
               .map(n -> n * n)
               .forEach(System.out::println);

        // Convert sequential to parallel
        numbers.stream()
               .parallel()
               .map(n -> n * n)
               .toList();

        // Check if stream is parallel
        boolean isParallel = numbers.parallelStream().isParallel();

        // Convert parallel to sequential
        numbers.parallelStream()
               .sequential()
               .toList();
    }
}
```

### When to Use Parallel Streams

| Use Parallel Streams | Avoid Parallel Streams |
|---------------------|----------------------|
| Large datasets (N > 10,000) | Small datasets |
| Computationally intensive operations | Simple operations (cheaper than threading overhead) |
| Stateless operations | Operations requiring strict ordering |
| No shared mutable state | Operations with side effects |
| ArrayList, IntStream.range | LinkedList, Stream.iterate |

```java
import java.util.List;

public class ParallelBestPractices {
    public static void main(String[] args) {
        List<Integer> largeList = java.util.stream.IntStream.range(1, 10_000_000)
                .boxed()
                .toList();

        // Good: computationally intensive, large dataset
        long start = System.currentTimeMillis();
        double result = largeList.parallelStream()
                .mapToDouble(Math::sqrt)
                .map(Math::sin)
                .sum();
        System.out.println("Parallel time: " + (System.currentTimeMillis() - start) + "ms");
    }
}
```

---

## Common Patterns

### Filtering and Transformation

```java
import java.util.List;

public class FilterTransformPattern {
    record Employee(String name, String department, double salary, boolean active) {}

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "Engineering", 90000, true),
                new Employee("Bob", "Sales", 60000, false),
                new Employee("Charlie", "Engineering", 100000, true)
        );

        // Pattern: filter -> map -> collect
        List<String> activeEngineerNames = employees.stream()
                .filter(Employee::active)
                .filter(e -> "Engineering".equals(e.department))
                .map(Employee::name)
                .toList();
    }
}
```

### Pipeline of Operations

```java
import java.util.*;
import java.util.stream.Collectors;

public class PipelinePattern {
    record Order(String customer, double amount, String status) {}

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Alice", 150.0, "COMPLETED"),
                new Order("Bob", 80.0, "PENDING"),
                new Order("Alice", 200.0, "COMPLETED"),
                new Order("Charlie", 300.0, "COMPLETED")
        );

        // Find top 2 customers by total completed order amount
        Map<String, Double> topCustomers = orders.stream()
                .filter(o -> "COMPLETED".equals(o.status))
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.summingDouble(Order::amount)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(2)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
```

### Null Safety with Streams

```java
import java.util.*;
import java.util.stream.Stream;

public class NullSafety {
    public static void main(String[] args) {
        List<String> listWithNulls = Arrays.asList("a", null, "b", null, "c");

        // Filter out nulls
        List<String> nonNull = listWithNulls.stream()
                .filter(Objects::nonNull)
                .toList(); // [a, b, c]

        // Handle nullable values with Optional
        String value = null;
        List<String> fromNullable = Stream.ofNullable(value)
                .toList(); // []
    }
}
```

---

## Best Practices

### ✅ Do's

1. **Prefer method references** when they improve readability
   ```java
   // Good
   list.stream().map(String::toUpperCase)
   
   // Also acceptable for clarity
   list.stream().map(s -> s.toUpperCase())
   ```

2. **Use `toList()` (Java 16+)** for unmodifiable lists
   ```java
   List<String> result = stream.toList();
   ```

3. **Leverage Optional** to avoid null checks
   ```java
   stream.filter(Objects::nonNull)
         .findFirst()
         .orElse("default");
   ```

4. **Use primitive streams** for numeric operations
   ```java
   // Better performance
   int sum = list.stream().mapToInt(Integer::intValue).sum();
   ```

5. **Keep streams pure** — avoid side effects
   ```java
   // Bad: side effect
   List<Integer> result = new ArrayList<>();
   stream.forEach(result::add);
   
   // Good: use collector
   List<Integer> result = stream.toList();
   ```

### ❌ Don'ts

1. **Don't reuse a stream**
   ```java
   Stream<String> stream = list.stream();
   stream.forEach(System.out::println);
   stream.filter(...); // IllegalStateException!
   ```

2. **Don't modify the source during stream operation**
   ```java
   List<String> list = new ArrayList<>(...);
   list.stream().forEach(list::remove); // ConcurrentModificationException!
   ```

3. **Don't overuse parallel streams**
   ```java
   // Bad: small dataset, simple operation
   List.of(1, 2, 3).parallelStream().map(n -> n * 2).toList();
   ```

4. **Don't use streams for everything**
   ```java
   // Simple iteration is clearer
   for (var item : list) {
       if (item.isActive()) process(item);
   }
   ```

5. **Don't use `reduce` when a specific collector exists**
   ```java
   // Overly complex
   int sum = stream.reduce(0, Integer::sum);
   
   // Better
   int sum = stream.mapToInt(Integer::intValue).sum();
   ```

---

## Stream Pipeline Lifecycle

```
┌─────────────────────────────────────────────────────────────┐
│                    STREAM PIPELINE                           │
├─────────────┬───────────────────────┬───────────────────────┤
│   Source    │  Intermediate Ops     │   Terminal Op         │
│             │  (lazy, chained)      │   (eager, triggers)   │
├─────────────┼───────────────────────┼───────────────────────┤
│ Collection  │ → filter(predicate)   │ → forEach()           │
│ Array       │ → map(function)       │ → collect()           │
│ Stream.of() │ → flatMap(function)   │ → reduce()            │
│ Files.lines()│ → sorted()           │ → count()             │
│ IntStream   │ → distinct()          │ → anyMatch/allMatch   │
│   .range()  │ → limit(n)            │ → findFirst/findAny   │
│             │ → skip(n)             │ → min/max             │
│             │ → peek(consumer)      │ → toArray             │
└─────────────┴───────────────────────┴───────────────────────┘
```

---

## Quick Reference

| Operation | Type | Description |
|-----------|------|-------------|
| `filter` | Intermediate | Keep elements matching predicate |
| `map` | Intermediate | Transform each element |
| `flatMap` | Intermediate | Transform and flatten |
| `distinct` | Intermediate | Remove duplicates |
| `sorted` | Intermediate | Sort elements |
| `peek` | Intermediate | Inspect elements (debugging) |
| `limit` | Intermediate | Keep first N elements |
| `skip` | Intermediate | Skip first N elements |
| `forEach` | Terminal | Apply action to each element |
| `collect` | Terminal | Accumulate into a collection |
| `reduce` | Terminal | Combine into a single value |
| `count` | Terminal | Count elements |
| `anyMatch` | Terminal | Check if any match |
| `allMatch` | Terminal | Check if all match |
| `noneMatch` | Terminal | Check if none match |
| `findFirst` | Terminal | Find first element |
| `findAny` | Terminal | Find any element |
| `min` | Terminal | Find minimum |
| `max` | Terminal | Find maximum |

---

## Version Notes

- **Java 8**: Streams API introduced
- **Java 9**: `takeWhile`, `dropWhile`, `ofNullable`, `iterate` with predicate
- **Java 16**: `toList()` (unmodifiable), `mapMulti`
- **Java 21**: Gatherers (preview) for more powerful stream operations

---

> 💡 **Remember**: Streams excel at expressing *what* you want to achieve, not *how* to achieve it. This declarative style leads to cleaner, more maintainable code that naturally lends itself to parallelization.
