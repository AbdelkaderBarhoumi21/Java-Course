package projects.AnimalKingdom;

public class Penguin implements Animal, Swimmable, Runnable {
    public void eat() {
        System.out.println("Penguin eating fish");
    }

    public void swim() {
        System.out.println("Penguin swimming");
    }

    public void run() {
        System.out.println("Penguin waddling");
    }

    public String name() {
        return "Penguin";
    }
}
