package solid;

/*
 * Liskov Substitution Principle (LSP)
 * -----------------------------------
 * Subtypes must be substitutable for their base types
 * without breaking the program.
 *
 * One diagram to remember it all:
 *
 *   ❌ Violation                       ✅ Correct
 *   -----------                       ----------
 *   Bird (promises fly())             Bird interface (eat, breathe)
 *    ├── Eagle  -> fly() ✅            ├── FlyingBird (+ fly())
 *    └── Penguin-> fly() throws ❌     │     └── Eagle -> fly() ✅
 *                                      └── Penguin (no fly()) ✅
 *
 *   Bird b = new Penguin();           Penguin won't even compile
 *   b.fly(); -> CRASH at runtime      as a FlyingBird ✅
 */
public class liskov_substitution_example {

    public static void main(String[] args) {

        // ❌ Violation demo — compiles, but crashes at runtime
        System.out.println("--- Violation ---");
        BadBird badEagle = new BadEagle();
        BadBird badPenguin = new BadPenguin();
        badEagle.fly();
        try {
            badPenguin.fly(); // 💥 surprise! breaks LSP
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // ✅ Correct demo — only flying birds expose fly()
        System.out.println("\n--- Correct ---");
        Bird eagle = new Eagle();
        Bird penguin = new Penguin();

        eagle.eat();
        penguin.eat();

        FlyingBird flyingEagle = new Eagle();
        flyingEagle.fly();

        // FlyingBird flyingPenguin = new Penguin(); // ❌ won't compile — good!
    }
}

/*
 * =====================================================================
 * ❌ VIOLATION: base type promises fly() that not all subtypes can keep
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
        throw new RuntimeException("Penguins can't fly!"); // ❌ LSP broken
    }
}

/*
 * =====================================================================
 * ✅ CORRECT: split capability into a narrower interface (FlyingBird)
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
    // no fly() — honest ✅ penguins never promised to fly
}
