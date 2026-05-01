package solid;

/*
 * Dependency Inversion Principle (DIP)
 * ------------------------------------
 * Rule 1: High-level modules should NOT depend on low-level modules.
 *         Both should depend on abstractions.
 * Rule 2: Abstractions should NOT depend on details.
 *         Details should depend on abstractions.
 *
 * One diagram to remember it all:
 *
 *   âŒ Violation                          âœ… Correct
 *   -----------                          ----------
 *   UserService                          UserService
 *      â”‚ (hardcoded `new MySQLDatabase`)    â”‚ depends on
 *      â–¼                                    â–¼
 *   MySQLDatabase  (concrete)            Database (interface)
 *                                          â–²   â–²   â–²
 *                                          â”‚   â”‚   â”‚
 *                                       MySQL Mongo Postgres
 *
 *   Change DB = rewrite UserService âŒ   Swap DB freely, UserService unchanged âœ…
 */
public class DependencyInversionExample {

    public static void main(String[] args) {

        // âŒ Violation demo â€” locked to MySQL
        System.out.println("--- Violation ---");
        BadUserService bad = new BadUserService();
        bad.saveUser("Alice");
        // want MongoDB? edit BadUserService source â€” no other way âŒ

        // âœ… Correct demo â€” same UserService, three databases
        System.out.println("\n--- Correct ---");
        UserService mysqlService    = new UserService(new MySQLDatabase());
        UserService mongoService    = new UserService(new MongoDatabase());
        UserService postgresService = new UserService(new PostgresDatabase());

        mysqlService.saveUser("Alice");
        mongoService.saveUser("Bob");
        postgresService.saveUser("Carol");
    }
}

/* =====================================================================
 * âŒ VIOLATION: high-level class hardcodes a concrete low-level class
 * ===================================================================== */
class BadMySQLDatabase {
    void save(String data) {
        System.out.println("Saving to MySQL : " + data);
    }
}

class BadUserService {
    // âŒ hardcoded â€” UserService is glued to MySQL forever
    private final BadMySQLDatabase db = new BadMySQLDatabase();

    void saveUser(String name) {
        db.save(name);
    }
}

/* =====================================================================
 * âœ… CORRECT: depend on an abstraction; inject the concrete from outside
 * ===================================================================== */

// Step 1 â€” the abstraction (the contract)
interface Database {
    void save(String data);
}

// Step 2 â€” low-level classes implement the contract
class MySQLDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to MySQL : " + data);
    }
}

class MongoDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to MongoDB : " + data);
    }
}

class PostgresDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to Postgres : " + data);
    }
}

// Step 3 â€” high-level class depends ONLY on the interface
class UserService {
    private final Database db; // âœ… knows only about Database â€” nothing specific

    // dependency is INJECTED from outside, not created inside
    UserService(Database db) {
        this.db = db;
    }

    void saveUser(String name) {
        db.save(name); // âœ… calls the contract â€” doesn't care who answers
    }
}
