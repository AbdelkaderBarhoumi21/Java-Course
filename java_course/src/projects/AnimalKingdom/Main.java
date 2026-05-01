package projects.AnimalKingdom;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Animal> animals = List.of(new Eagle(), new Dolphin(), new Duck(), new Penguin());

        // Polymorphism — same call, different behavior
        System.out.println("--- All animals eat ---");
        animals.forEach(a -> {
            System.out.print(a.name() + ": ");
            a.eat();
        });

        // Only flying animals
        System.out.println("--- Flying animals ---");
        animals.stream()
                .filter(a -> a instanceof Flyable)
                .map(a -> (Flyable) a)
                .forEach(Flyable::fly);

        // Only swimming animals
        System.out.println("--- Swimming animals ---");
        animals.stream()
                .filter(a -> a instanceof Swimmable)
                .map(a -> (Swimmable) a)
                .forEach(Swimmable::swim);
    }
}
