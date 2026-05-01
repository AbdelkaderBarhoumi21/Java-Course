package projects.AnimalKingdom;

public class Eagle implements Animal, Flyable, Runnable {
    public void eat() {
        System.out.println("Eagle eating a fish");
    }

    public void fly() {
        System.out.println("Eagle soaring high");
    }

    public void run() {
        System.out.println("Eagle running on land");
    }

    public String name() {
        return "Eagle";
    }
}
