package oop;

/**
 * Inheritance:
 * Inheritance lets you create a new class based on an existing one.
 * The child class inherits the attributes and methods of the parent class.
 *
 * Key concepts:
 * - "extends"   — keyword used to inherit from another class.
 * - "super(...)"— calls the parent's constructor. Must be the FIRST statement
 *                 of the child constructor.
 * - "@Override" — annotation that marks a method as redefining a parent method.
 *                 The compiler checks that the method actually exists in the parent.
 * - "protected" — accessible in subclasses (unlike "private").
 *
 * Note: a .java file can contain only one public class, so Animal, Dog, and Cat
 * are declared without the "public" modifier here.
 */
public class inheritance {

    public static void main(String[] args) {
        Dog rex = new Dog("Rex", 3, "German Shepherd");
        rex.eat();                           // Rex is eating.       (inherited from Animal)
        rex.bark();                          // Rex barks: Woof!     (defined in Dog)
        System.out.println(rex.introduce()); // Rex (3 yrs) - Breed: German Shepherd

        Cat mimi = new Cat("Mimi", 5, true);
        mimi.sleep();                        // Mimi is sleeping. Zzz... (inherited from Animal)
        mimi.purr();                         // Mimi is purring...
        System.out.println(mimi.introduce());
    }
}

// Parent class (superclass)
class Animal {

    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping. Zzz...");
    }

    public String introduce() {
        return name + " (" + age + " yrs)";
    }
}

// Child class (subclass)
class Dog extends Animal {

    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    public void bark() {
        System.out.println(name + " barks: Woof!");
    }

    @Override
    public void eat() {
        super.eat();
    }

    @Override
    public String introduce() {
        return super.introduce() + " - Breed: " + breed;
    }
}

// Another subclass
class Cat extends Animal {

    private boolean isIndoor;

    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }

    public void purr() {
        System.out.println(name + " is purring...");
    }

    @Override
    public String introduce() {
        String type = isIndoor ? "indoor" : "outdoor";
        return super.introduce() + " - " + type + " cat";
    }
}
