package projects.ShapeCalculator;

public sealed interface Shape
        permits Circle, Rectangle, Triangle, Square {
}

// Each shape is a Record — immutable, clean, no boilerplate
record Circle(double radius) implements Shape {
}

record Rectangle(double width, double height) implements Shape {
}

record Triangle(double base, double height) implements Shape {
}

record Square(double side) implements Shape {
}