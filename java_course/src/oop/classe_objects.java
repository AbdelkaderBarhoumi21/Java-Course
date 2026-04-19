package oop;

/**
 * Classes and objects in Java: - A class is a blueprint; an object is an
 * instance created with "new". - Attributes (fields) hold the object's state. -
 * The constructor is called when the object is created and initializes its
 * fields. - "this" refers to the current object — used to distinguish a field
 * from a parameter with the same name. - Methods define the behavior the object
 * can perform.
 *
 * Note: a .java file can contain only one public class, and its name must match
 * the filename. That's why "Car" below is declared without the "public"
 * modifier.
 */
public class classe_objects {

    public static void main(String[] args) {
        Car myCar = new Car("Toyota", "Red");
        myCar.accelerate(50);    // Toyota is driving at 50 km/h
        myCar.accelerate(30);    // Toyota is driving at 80 km/h
        myCar.brake();           // Toyota has stopped

        Car otherCar = new Car("BMW", "Black");
        otherCar.accelerate(100); // BMW is driving at 100 km/h
    }
}

class Car {

    // Attributes (properties)
    String brand;
    String color;
    int speed;

    // Constructor — called when the object is created
    public Car(String brand, String color) {
        this.brand = brand;   // "this" refers to the current object
        this.color = color;
        this.speed = 0;
    }

    // Method
    public void accelerate(int increment) {
        speed += increment;
        System.out.println(brand + " is driving at " + speed + " km/h");
    }

    public void brake() {
        speed = 0;
        System.out.println(brand + " has stopped");
    }
}
