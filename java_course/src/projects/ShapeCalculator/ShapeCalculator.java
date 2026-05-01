package projects.ShapeCalculator;

public class ShapeCalculator {
    public static double area(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
            case Triangle t -> (t.base() * t.height()) / 2;
            case Square s -> s.side() * s.side();
        };
    }

    public static String describe(Shape shape) {
        return switch (shape) {
            case Circle c -> "A circle with radius " + c.radius();
            case Rectangle r -> "A rectangle " + r.width() + "x" + r.height();
            case Triangle t -> "A triangle with base " + t.base();
            case Square s -> "A square with side " + s.side();
        };
    }

}
