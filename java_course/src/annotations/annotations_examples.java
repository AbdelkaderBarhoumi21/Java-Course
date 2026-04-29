package annotations;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class demonstrates the most commonly used Java annotations.
 *
 * Annotations provide metadata about code. They do not directly affect program
 * logic,
 * but they can be read by the compiler, tools (IDE, linter), or at runtime via
 * reflection.
 *
 * Built-in annotations covered here:
 * - @Override : Marks that a method overrides a superclass method.
 * - @Deprecated : Marks an element as obsolete and discourages its use.
 * - @SuppressWarnings : Tells the compiler to ignore specific warnings.
 * - @FunctionalInterface : Ensures an interface has exactly one abstract
 * method.
 * - @SafeVarargs : Suppresses warnings for varargs methods with generic arrays.
 *
 * Custom annotations:
 * - @Retention : Specifies how long an annotation is retained (SOURCE, CLASS,
 * RUNTIME).
 * - @Target : Restricts where an annotation can be applied (METHOD, FIELD,
 * CLASS, etc.).
 * - @interface : Declares a new custom annotation type.
 */
public class annotations_examples {

    public static void main(String[] args) {

        // -------------------------------------------------
        // @Override example
        // -------------------------------------------------
        System.out.println("--- @Override ---");
        Animal dog = new Dog();
        System.out.println(dog); // Uses overridden toString()

        // -------------------------------------------------
        // @Deprecated example
        // -------------------------------------------------
        System.out.println("\n--- @Deprecated ---");
        LegacyApi api = new LegacyApi();
        api.newMethod();
        // api.oldMethod(); // compile warning: deprecated and marked for removal

        // -------------------------------------------------
        // @SuppressWarnings example
        // -------------------------------------------------
        System.out.println("\n--- @SuppressWarnings ---");
        WarningDemo demo = new WarningDemo();
        demo.uncheckedCastExample();

        // -------------------------------------------------
        // @FunctionalInterface example
        // -------------------------------------------------
        System.out.println("\n--- @FunctionalInterface ---");
        Action sayHello = () -> System.out.println("Hello from functional interface!");
        sayHello.execute();

        // Using a method reference that matches the functional interface
        Action printInfo = System.out::println;
        // printInfo.execute(); // would need a String argument, so we skip here

        // -------------------------------------------------
        // @SafeVarargs example
        // -------------------------------------------------
        System.out.println("\n--- @SafeVarargs ---");
        Printer.printItems("Java", "Python", "Go");

        // -------------------------------------------------
        // Custom annotation via reflection
        // -------------------------------------------------
        System.out.println("\n--- Custom Annotation (@Author) ---");
        Class<Book> clazz = Book.class;
        if (clazz.isAnnotationPresent(BookAuthor.class)) {
            BookAuthor author = clazz.getAnnotation(BookAuthor.class);
            System.out.println("Book author: " + author.name());
            System.out.println("Version: " + author.version());
        }
    }
}

// ============================================================
// @Override — indicates a method is overriding a superclass method.
// Helps catch typos at compile time (e.g., wrong method signature).
// ============================================================
class Animal {
    @Override
    public String toString() {
        return "Animal{}";
    }
}

class Dog extends Animal {
    /// Overrides toString() from Object via Animal. Without @Override, a typo like
    /// "toStrring()" would silently create a new method.
    @Override
    public String toString() {
        return "Dog{name='Buddy'}";
    }
}

// ============================================================
// @Deprecated — marks a method/class/field as obsolete.
// Can include since="x.x" and forRemoval=true for clarity.
// ============================================================
class LegacyApi {

    /// Modern replacement for the legacy method.
    public void newMethod() {
        System.out.println("Using the new, recommended method.");
    }

    /// This method is obsolete and scheduled for removal in a future release.
    @Deprecated(since = "2.0", forRemoval = true)
    public void oldMethod() {
        System.out.println("This method is deprecated!");
    }
}

// ============================================================
// @SuppressWarnings — tells the compiler to suppress specific warnings.
// Common values: "unchecked", "rawtypes", "deprecation", "unused"
// Use sparingly — only when you are certain the code is safe.
// ============================================================
class WarningDemo {

    /// Demonstrates suppressing "unchecked" warnings for a cast that is
    /// guaranteed safe.
    @SuppressWarnings("unchecked")
    public void uncheckedCastExample() {
        List rawList = new ArrayList();
        rawList.add("hello");

        // Casting raw list to generic list would normally produce an unchecked warning.
        List<String> safeList = rawList;
        System.out.println("First item: " + safeList.get(0));
    }
}

// ============================================================
// @FunctionalInterface — ensures the interface has exactly ONE abstract method.
// The compiler will reject any interface annotated with this that violates the
// rule.
// Such interfaces are the foundation of lambda expressions and method
// references.
// ============================================================

/// Represents a single action with no arguments and no return value. Annotated
/// to guarantee it remains a valid functional interface.
@FunctionalInterface
interface Action {
    void execute();

    // default methods do not count against the single-abstract-method rule
    default void executeTwice() {
        execute();
        execute();
    }
}

// ============================================================
// @SafeVarargs — suppresses heap-pollution warnings on varargs methods
// that use generic types. Must be applied to final or static methods.
// ============================================================
class Printer {

    /// Safe because the method only reads the varargs; it does not expose
    /// the array.
    @SafeVarargs
    public static <T> void printItems(T... items) {
        System.out.println("Items: " + Arrays.toString(items));
    }
}

// ============================================================
// Custom Annotation Example
// ============================================================

/// Meta-annotation: this annotation is available at runtime via reflection.
@Retention(RetentionPolicy.RUNTIME)

/// Meta-annotation: this annotation can only be applied to classes (TYPE).
@Target(ElementType.TYPE)

/// Custom annotation to attach author metadata to a class.
@interface BookAuthor {
    String name();

    double version() default 1.0;
}

/// A class annotated with our custom @BookAuthor annotation.
@BookAuthor(name = "Jane Doe", version = 1.2)
class Book {
    private final String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
