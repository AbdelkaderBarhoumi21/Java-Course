package design_patterns.decorator;

/*
 * Decorator Pattern
 * -----------------
 * Wraps an object to add behavior WITHOUT changing its class
 * and WITHOUT subclass explosion.
 *
 *   Coffee c = new Milk(new Sugar(new SimpleCoffee()));
 *
 * Each wrapper implements the same interface and delegates to the inner one,
 * adding a little extra before/after.
 *
 * When to use:
 *   - Stackable, optional features (logging, caching, compression, auth).
 *   - You'd otherwise need MilkSugarCoffee, MilkOnlyCoffee, SugarOnlyCoffee, ...
 */
public class DecoratorExample {

    public static void main(String[] args) {
        Coffee plain = new SimpleCoffee();
        System.out.println(plain.description() + " = " + plain.cost());

        Coffee fancy = new Milk(new Sugar(new SimpleCoffee()));
        System.out.println(fancy.description() + " = " + fancy.cost());
    }
}

interface Coffee {
    String description();
    double cost();
}

class SimpleCoffee implements Coffee {
    public String description() { return "Coffee"; }
    public double cost()        { return 2.0; }
}

// Base decorator — holds and delegates to the wrapped Coffee
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee inner;

    protected CoffeeDecorator(Coffee inner) {
        this.inner = inner;
    }
}

class Milk extends CoffeeDecorator {
    public Milk(Coffee inner) { super(inner); }
    public String description() { return inner.description() + " + milk"; }
    public double cost()        { return inner.cost() + 0.5; }
}

class Sugar extends CoffeeDecorator {
    public Sugar(Coffee inner) { super(inner); }
    public String description() { return inner.description() + " + sugar"; }
    public double cost()        { return inner.cost() + 0.2; }
}
