package oop;

/**
 * Demonstrates Java Sealed Classes (finalized in Java 17).
 *
 * A sealed class or interface restricts which other classes or interfaces may
 * extend it.
 * It provides fine-grained inheritance control.
 *
 * Key concepts:
 * - sealed : Declares a class/interface that limits its subclasses.
 * - permits : Lists the classes explicitly allowed to extend the sealed type.
 * - final : A permitted subclass cannot be extended further.
 * - non-sealed : A permitted subclass is open for further extension (no
 * restrictions).
 * - sealed : A permitted subclass can also be sealed, continuing the chain.
 *
 * Benefits:
 * - Switch expressions can be exhaustive without a default branch.
 * - The compiler knows every possible subtype, enabling safer pattern matching.
 */
public class SealedClass {

    public static void describe(Shape s) {
        // Exhaustive switch â€” no default needed because the compiler
        // knows every permitted subtype of Shape.
        switch (s) {
            case Circle c -> System.out.println("Circle");
            case Rectangle r -> System.out.println("Rectangle");
            case Triangle t -> System.out.println("Triangle");
        }
        ;
    }

    public static void main(String[] args) {
        Circle c = new Circle(5);
        describe(c);
    }
}

/// Sealed abstract class that only permits Circle, Rectangle, and Triangle.
/// Subclasses must be final, sealed, or non-sealed.
sealed abstract class Shape permits Circle, Rectangle, Triangle {
    public abstract double area();
}

/// Final subclass â€” cannot be extended any further.
final class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

/// Final subclass â€” cannot be extended any further.
final class Rectangle extends Shape {
    private final double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

/// Non-sealed subclass â€” open for further extension by other classes.
non-sealed class Triangle extends Shape {
    private final double base, height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return (base * height) / 2;
    }
}
