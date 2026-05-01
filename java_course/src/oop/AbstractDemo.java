package oop;

/**
 * Abstract classes: - An abstract class cannot be instantiated directly (new
 * AbstractClass() is forbidden). - It can contain abstract methods (no body)
 * that subclasses MUST implement, as well as concrete methods shared by all
 * subclasses. - Marked with the "abstract" keyword before both the class and
 * the abstract methods. - Useful when you want to define a common
 * contract/behavior while leaving some details up to each subclass.
 *
 * Note: "abstract" is a Java reserved word, so the public class in this file is
 * named "abstractDemo" rather than "abstract". A .java file can only contain
 * one public class; Shape, Circle, and Rectangle are declared without the
 * "public" modifier.
 */
public class abstractDemo {

    public static void main(String[] args) {
        // Shape s = new Shape("red");  // ERROR — cannot instantiate an abstract class

        Shape circle = new Circle("blue", 5);
        Shape rect = new Rectangle("green", 4, 6);

        circle.describe(); // Shape blue | Area = 78.54 | Perimeter = 31.42
        rect.describe();   // Shape green | Area = 24.00 | Perimeter = 20.00
    }
}

abstract class Shape {

    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // Abstract methods — NO body, subclasses MUST implement them
    public abstract double computeArea();

    public abstract double computePerimeter();

    // Concrete method — available to all subclasses
    public void describe() {
        System.out.println("Shape " + color
                + " | Area = " + String.format("%.2f", computeArea())
                + " | Perimeter = " + String.format("%.2f", computePerimeter()));
    }
}

class Circle extends Shape {

    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double computeArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double computePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {

    private double width, height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double computeArea() {
        return width * height;
    }

    @Override
    public double computePerimeter() {
        return 2 * (width + height);
    }
}
