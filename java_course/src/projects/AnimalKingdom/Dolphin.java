package projects.AnimalKingdom;

public class Dolphin implements Animal, Swimmable {
    public void eat() {
        System.out.println("Dolphin eating squid");
    }

    public void swim() {
        System.out.println("Dolphin swimming fast");
    }

    public String name() {
        return "Dolphin";
    }
}
