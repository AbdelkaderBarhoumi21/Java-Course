package solid;

/*
 * Interface Segregation Principle (ISP)
 * -------------------------------------
 * Clients should not be forced to depend on methods
 * they do not use. Prefer many small, focused interfaces
 * over one fat "do-it-all" interface.
 *
 * One diagram to remember it all:
 *
 *   âŒ Violation                       âœ… Correct
 *   -----------                       ----------
 *   Worker (work, eat, sleep,         Workable    -> work()
 *           recharge, takeBreak)      Eatable     -> eat()
 *    â”œâ”€â”€ Human  -> recharge() âŒ      Sleepable   -> sleep()
 *    â””â”€â”€ Robot  -> eat()/sleep() âŒ   Rechargeable-> recharge()
 *                                     Breakable   -> takeBreak()
 *
 *   Forces empty / nonsense methods   Human  implements only what humans do âœ…
 *   on classes that don't need them   Robot  implements only what robots do âœ…
 */
public class InterfaceSegregationExamples {
    static void assignTask(Workable worker) {
        worker.work();
    }

    static void sendToLunch(Eatable entity) {
        entity.eat();

    }

    public static void main(String[] args) {

        assignTask(new Human1());
        assignTask(new Robot1());

        sendToLunch(new Human1());
        // sendToLunch(new Robot()); // âŒ won't compile â€” Robot isn't Eatable

    }

}

// âŒ ONE big interface that tries to cover everything
interface Worker {
    void work();

    void eat();

    void sleep();

    void recharge(); // robots recharge, humans don't

    void takeBreak(); // humans take breaks, robots don't
}

// âŒ Human forced to implement recharge() â€” makes no sense
class Human implements Worker {
    public void work() {
        System.out.println("Human working");
    }

    public void eat() {
        System.out.println("Human eating");
    }

    public void sleep() {
        System.out.println("Human sleeping");
    }

    public void recharge() {
        /* ...what do I put here?? */ } // âŒ nonsense

    public void takeBreak() {
        System.out.println("Human on break");
    }
}

// âŒ Robot forced to implement eat() and sleep() â€” makes no sense
class Robot implements Worker {
    public void work() {
        System.out.println("Robot working");
    }

    public void eat() {
        /* robots don't eat... */ } // âŒ nonsense

    public void sleep() {
        /* robots don't sleep... */ } // âŒ nonsense

    public void recharge() {
        System.out.println("Robot recharging");
    }

    public void takeBreak() {
        /* robots don't break... */ } // âŒ nonsense
}

// âœ… Small focused interfaces â€” each does ONE thing
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

interface Rechargeable {
    void recharge();
}

interface Breakable {
    void takeBreak();
}

// âœ… Human only implements what humans actually do
class Human1 implements Workable, Eatable, Sleepable, Breakable {
    public void work() {
        System.out.println("Human working");
    }

    public void eat() {
        System.out.println("Human eating");
    }

    public void sleep() {
        System.out.println("Human sleeping");
    }

    public void takeBreak() {
        System.out.println("Human on break");
    }
    // no recharge() â€” humans don't recharge âœ…
}

// âœ… Robot only implements what robots actually do
class Robot1 implements Workable, Rechargeable {
    public void work() {
        System.out.println("Robot working");
    }

    public void recharge() {
        System.out.println("Robot recharging");
    }
    // no eat(), sleep(), takeBreak() â€” robots don't do those âœ…
}