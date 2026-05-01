package solid;

/*
 * Liskov Substitution Principle (LSP)
 * -----------------------------------
 * Subtypes must be substitutable for their base types
 * without breaking the program.
 *
 * One diagram to remember it all:
 *
 *   âŒ Violation                       âœ… Correct
 *   -----------                       ----------
 *   Bird (promises fly())             Bird interface (eat, breathe)
 *    â”œâ”€â”€ Eagle  -> fly() âœ…            â”œâ”€â”€ FlyingBird (+ fly())
 *    â””â”€â”€ Penguin-> fly() throws âŒ     â”‚     â””â”€â”€ Eagle -> fly() âœ…
 *                                      â””â”€â”€ Penguin (no fly()) âœ…
 *
 *   Bird b = new Penguin();           Penguin won't even compile
 *   b.fly(); -> CRASH at runtime      as a FlyingBird âœ…
 */
public class LiskovSubstitutionExample {

    public static void main(String[] args) {

        // âŒ Violation demo â€” compiles, but crashes at runtime
        System.out.println("--- Violation ---");
        BadBird badEagle = new BadEagle();
        BadBird badPenguin = new BadPenguin();
        badEagle.fly();
        try {
            badPenguin.fly(); // ðŸ’¥ surprise! breaks LSP
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // âœ… Correct demo â€” only flying birds expose fly()
        System.out.println("\n--- Correct ---");
        Bird eagle = new Eagle();
        Bird penguin = new Penguin();

        eagle.eat();
        penguin.eat();

        FlyingBird flyingEagle = new Eagle();
        flyingEagle.fly();

        // FlyingBird flyingPenguin = new Penguin(); // âŒ won't compile â€” good!
    }
}

/*
 * =====================================================================
 * âŒ VIOLATION: base type promises fly() that not all subtypes can keep
 * =====================================================================
 */
abstract class BadBird {
    abstract void fly();
}

class BadEagle extends BadBird {
    @Override
    void fly() {
        System.out.println("Eagle flies");
    }
}

class BadPenguin extends BadBird {
    @Override
    void fly() {
        throw new RuntimeException("Penguins can't fly!"); // âŒ LSP broken
    }
}

/*
 * =====================================================================
 * âœ… CORRECT: split capability into a narrower interface (FlyingBird)
 * =====================================================================
 */
interface Bird {
    void eat();

    void breathe();
}

interface FlyingBird extends Bird {
    void fly();
}

class Eagle implements FlyingBird {
    @Override
    public void eat() {
        System.out.println("Eagle eats");
    }

    @Override
    public void breathe() {
        System.out.println("Eagle breathes");
    }

    @Override
    public void fly() {
        System.out.println("Eagle flies");
    }
}

class Penguin implements Bird {
    @Override
    public void eat() {
        System.out.println("Penguin eats");
    }

    @Override
    public void breathe() {
        System.out.println("Penguin breathes");
    }
    // no fly() â€” honest âœ… penguins never promised to fly
}
