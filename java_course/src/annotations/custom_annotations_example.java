package annotations;

import java.lang.annotation.*;

public class custom_annotations_example {
    public static void main(String[] args) {

        Author a = Car.class.getAnnotation(Author.class);
        System.out.println(a.name());
        System.out.println(a.year());

    }

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Author {
    String name(); // required 

    int year() default 2026; // optional 
}

@Author(name = "John", year = 2025)
class Car {
    String model = "Toyota";
}