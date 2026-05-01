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
 *   ❌ Violation                       ✅ Correct
 *   -----------                       ----------
 *   Worker (work, eat, sleep,         Workable    -> work()
 *           recharge, takeBreak)      Eatable     -> eat()
 *    ├── Human  -> recharge() ❌      Sleepable   -> sleep()
 *    └── Robot  -> eat()/sleep() ❌   Rechargeable-> recharge()
 *                                     Breakable   -> takeBreak()
 *
 *   Forces empty / nonsense methods   Human  implements only what humans do ✅
 *   on classes that don't need them   Robot  implements only what robots do ✅
 */
public class interface_segregation_examples {
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
        // sendToLunch(new Robot()); // ❌ won't compile — Robot isn't Eatable

    }

}

// ❌ ONE big interface that tries to cover everything
interface Worker {
    void work();

    void eat();

    void sleep();

    void recharge(); // robots recharge, humans don't

    void takeBreak(); // humans take breaks, robots don't
}

// ❌ Human forced to implement recharge() — makes no sense
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
        /* ...what do I put here?? */ } // ❌ nonsense

    public void takeBreak() {
        System.out.println("Human on break");
    }
}

// ❌ Robot forced to implement eat() and sleep() — makes no sense
class Robot implements Worker {
    public void work() {
        System.out.println("Robot working");
    }

    public void eat() {
        /* robots don't eat... */ } // ❌ nonsense

    public void sleep() {
        /* robots don't sleep... */ } // ❌ nonsense

    public void recharge() {
        System.out.println("Robot recharging");
    }

    public void takeBreak() {
        /* robots don't break... */ } // ❌ nonsense
}

// ✅ Small focused interfaces — each does ONE thing
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

// ✅ Human only implements what humans actually do
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
    // no recharge() — humans don't recharge ✅
}

// ✅ Robot only implements what robots actually do
class Robot1 implements Workable, Rechargeable {
    public void work() {
        System.out.println("Robot working");
    }

    public void recharge() {
        System.out.println("Robot recharging");
    }
    // no eat(), sleep(), takeBreak() — robots don't do those ✅
}