package projects.ShapeCalculator;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Shape> shapes = List.of(
                new Circle(5),
                new Rectangle(4, 6),
                new Triangle(3, 8),
                new Square(2));

        for (Shape s : shapes) {
            System.out.printf("%s → Area: %.2f%n",
                    ShapeCalculator.describe(s),
                    ShapeCalculator.area(s));
        }
    }

}
