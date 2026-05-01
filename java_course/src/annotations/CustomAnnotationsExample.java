package annotations;

import java.lang.annotation.*;

/**
 * Demonstrates creating and reading a custom annotation at runtime.
 *
 * .class in Java â€” quick overview:
 *
 * Car.class is a Class Literal. It gives you the Class object that represents
 * the Car class itself â€” not an instance, but the blueprint/metadata of Car.
 *
 *   Car myCar = new Car();   // an INSTANCE of Car  â†’ the actual object
 *   Car.class                // the CLASS itself     â†’ metadata about Car
 *
 * Think of it like this:
 *   Car.class  =  "the blueprint of Car"
 *   new Car()  =  "a house built FROM the blueprint"
 *
 * What can you do with Car.class ?
 *   Class<Car> info = Car.class;
 *   info.getName();                   â†’ "Car"
 *   info.getDeclaredFields();         â†’ [model, color]
 *   info.getDeclaredMethods();        â†’ [drive, stop]
 *   info.getAnnotation(Author.class); â†’ reads @Author
 *   info.getSuperclass();             â†’ Object
 *
 * This is called Reflection â€” inspecting a class while the program is running.
 *
 * Three ways to get a Class object:
 *   1. Class literal (compile-time known)
 *      Class<Car> c1 = Car.class;
 *
 *   2. getClass() on an existing instance
 *      Car myCar = new Car();
 *      Class<?> c2 = myCar.getClass();
 *
 *   3. forName() from a String
 *      Class<?> c3 = Class.forName("Car");
 *
 * Simple analogy: Car.class is the COOKIE CUTTER, new Car() is the COOKIE.
 */
public class CustomAnnotationsExample {
    public static void main(String[] args) {

        // Read the @Author annotation from the Car blueprint (not an instance)
        Author a = Car.class.getAnnotation(Author.class);
        System.out.println(a.name());
        System.out.println(a.year());

    }

}

/// Make this annotation available at runtime so reflection can read it.
@Retention(RetentionPolicy.RUNTIME)

/// Restrict this annotation to classes (TYPE) only.
@Target(ElementType.TYPE)

/// Custom annotation with one required field (name) and one optional field (year).
@interface Author {
    String name();              // required
    int year() default 2026;    // optional
}

/// A class marked with our custom @Author annotation.
@Author(name = "John", year = 2025)
class Car {
    String model = "Toyota";
}
