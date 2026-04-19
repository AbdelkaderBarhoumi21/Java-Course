package oop;

/**
 * Polymorphism: Polymorphism lets you use a child-type object where a parent
 * type is expected. The correct method is called based on the REAL (runtime)
 * type of the object — this is called dynamic dispatch.
 *
 * instanceof and casting: - "instanceof" checks the real runtime type of an
 * object. - Pattern matching (Java 16+) performs the cast automatically, so you
 * can use the typed variable directly without writing "(Dog) a".
 *
 * Note: Animal, Dog, and Cat are defined in inheritance.java (same "oop"
 * package), so they are reused here without being redeclared.
 */
public class polymorphisme {

    // This method accepts any Animal (or subclass)
    public static void present(Animal animal) {
        System.out.println(animal.introduce());
    }

    public static void main(String[] args) {
        // Variable of type Animal, object of type Dog
        Animal myAnimal = new Dog("Rex", 3, "Labrador");
        present(myAnimal);
        // → Rex (3 yrs) - Breed: Labrador

        present(new Dog("Rex", 3, "Labrador"));
        // → Rex (3 yrs) - Breed: Labrador

        present(new Cat("Mimi", 5, true));
        // → Mimi (5 yrs) - indoor cat

        // Polymorphic array
        Animal[] animals = {
            new Dog("Buddy", 2, "Golden"),
            new Cat("Luna", 4, false),
            new Dog("Max", 5, "Husky")
        };

        for (Animal a : animals) {
            a.eat();             // Works for any Animal
            // a.bark();          // ERROR — Animal has no bark() method
        }

        System.out.println("---");

        // instanceof + pattern matching (Java 16+)
        for (Animal a : animals) {
            if (a instanceof Dog dog) {      // auto-cast to Dog
                dog.bark();                  // Dog-specific method
            } else if (a instanceof Cat cat) {
                cat.purr();                  // Cat-specific method
            }
        }
    }
}
