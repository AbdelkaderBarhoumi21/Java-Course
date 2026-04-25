package functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/*
 * Lambda Expressions demo.
 *
 * A lambda is a short way to write an implementation of a functional interface
 * (an interface with exactly one abstract method).
 *
 * Syntax:
 *   () -> expression                          // no parameters
 *   x -> expression                           // one parameter
 *   (x, y) -> expression                      // multiple parameters
 *   (x, y) -> { statements; return value; }   // multiple statements
 *
 * Examples in this file:
 *   1. Runnable          — lambda with no parameters
 *   2. Consumer<String>  — lambda with one parameter, returns nothing
 *   3. BiFunction        — lambda with two parameters, returns a value
 *   4. Function (block)  — multi-line lambda with { } and return
 *   5. List.forEach/sort — lambdas applied to collections
 *   6. Predicate         — lambda that returns true/false
 *   7. Method reference  — shortest form (System.out::println)
 */
public class lambda_expression {
    public static void main(String[] args) {

        // 1. Runnable — no parameters, no return value
        // create object of Runnable + override Run()
        Runnable greet = () -> System.out.println("Hello from a lambda!"); // body of run 
        // call YOUR overridden version
        greet.run(); // Hello from a lambda!

        // 2. Consumer — takes one input, returns nothing
        Consumer<String> printer = name -> System.out.println("Hi " + name);
        printer.accept("Alice"); // Hi Alice
        printer.accept("Bob"); // Hi Bob

        // 3. BiFunction — takes two inputs, returns a value
        // <t,u,r> => t the first function argument , u the second function argument ,
        // the function result
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        System.out.println("2 + 3 = " + add.apply(2, 3)); // 5
        System.out.println("4 * 5 = " + multiply.apply(4, 5)); // 20

        // 4. Function with a block body — multiple statements need { } and return
        //<T,R> T => the function argument , R the function result
        Function<Integer, String> describe = n -> {
            if (n > 0)
                return "positive";
            if (n < 0)
                return "negative";
            return "zero";
        };
        System.out.println(describe.apply(5)); // positive
        System.out.println(describe.apply(-3)); // negative
        System.out.println(describe.apply(0)); // zero

        // 5. Lambdas with collections — forEach and sort
        List<String> names = new ArrayList<>(List.of("Charlie", "Alice", "Bob"));
        names.forEach(name -> System.out.println("Name: " + name));
        names.sort((a, b) -> a.compareTo(b));
        System.out.println("Sorted: " + names); // [Alice, Bob, Charlie]

        // 6. Predicate — returns true / false
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<String> isEmpty = s -> s.isEmpty();
        System.out.println(isPositive.test(5)); // true
        System.out.println(isPositive.test(-3)); // false
        System.out.println(isEmpty.test("")); // true

        // 7. Method reference — shortest form, equivalent to (x) ->
        // System.out.println(x)
        names.forEach(System.out::println); // Alice / Bob / Charlie
    }
}

//