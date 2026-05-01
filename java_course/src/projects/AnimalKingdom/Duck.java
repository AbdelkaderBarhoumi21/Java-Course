package projects.AnimalKingdom;

public class Duck implements Animal, Flyable, Swimmable, Runnable {
    public void eat() {
        System.out.println("Duck eating bread");
    }

    public void fly() {
        System.out.println("Duck flying low");
    }

    public void swim() {
        System.out.println("Duck paddling");
    }

    public void run() {
        System.out.println("Duck waddling");
    }

    public String name() {
        return "Duck";
    }
}
