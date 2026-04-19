package oop;

/**
 * Interfaces:
 * An interface is a CONTRACT: it defines what a class must do, without saying how.
 * Since Java 8, interfaces can also provide methods with a body (default and static).
 *
 * Key differences between an abstract class and an interface:
 * | Feature       | Abstract class                 | Interface                                |
 * | ------------- | ------------------------------ | ---------------------------------------- |
 * | Inheritance   | Only one (extends)             | Several (implements)                     |
 * | Constructor   | Yes                            | No                                       |
 * | Fields        | Any type                       | public static final only (constants)     |
 * | Methods       | Abstract + concrete            | Abstract + default + static              |
 *
 * "default" vs "static" methods in interfaces:
 * - default  : has a body, inherited by the implementing class, and CAN be overridden.
 *              Called on an INSTANCE: duck.glide();
 *              Used to add new behavior to an interface without breaking existing implementers.
 * - static   : has a body, belongs to the INTERFACE itself, NOT inherited, CANNOT be overridden.
 *              Called on the INTERFACE: Flyer.convertAltitude(1000);
 *              Used for utility/helper methods related to the interface.
 *
 * Note: Animal is already defined in inheritance.java (same "oop" package),
 * so Duck reuses it via "extends".
 */
public class interface_class {

    public static void main(String[] args) {
        Duck donald = new Duck("Donald", 2);

        donald.eat();       // Inherited from Animal
        donald.takeOff();   // From Flyer interface
        donald.swim();      // From Swimmer interface
        donald.glide();     // default method from Flyer

        // Static method — called on the INTERFACE, not on an instance
        double meters = Flyer.convertAltitude(1000);
        System.out.println("1000 ft = " + meters + " m");
    }
}

// Interface
interface Flyer {

    void takeOff();

    void land();

    // Default method (Java 8+) — has a body, inherited by implementers
    default void glide() {
        System.out.println("Gliding through the air...");
    }

    // Static method — belongs to the interface, called as Flyer.convertAltitude(...)
    static double convertAltitude(double feet) {
        return feet * 0.3048; // feet -> meters
    }
}

interface Swimmer {

    void swim();

    void dive();
}

// A class can implement SEVERAL interfaces (comma-separated)
class Duck extends Animal implements Flyer, Swimmer {

    public Duck(String name, int age) {
        super(name, age);
    }

    @Override
    public void takeOff() {
        System.out.println(name + " takes off!");
    }

    @Override
    public void land() {
        System.out.println(name + " lands.");
    }

    @Override
    public void swim() {
        System.out.println(name + " is swimming on the water.");
    }

    @Override
    public void dive() {
        System.out.println(name + " dives underwater.");
    }
}
