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
 *   ❌ Violation                          ✅ Correct
 *   -----------                          ----------
 *   UserService                          UserService
 *      │ (hardcoded `new MySQLDatabase`)    │ depends on
 *      ▼                                    ▼
 *   MySQLDatabase  (concrete)            Database (interface)
 *                                          ▲   ▲   ▲
 *                                          │   │   │
 *                                       MySQL Mongo Postgres
 *
 *   Change DB = rewrite UserService ❌   Swap DB freely, UserService unchanged ✅
 */
public class dependency_inversion_example {

    public static void main(String[] args) {

        // ❌ Violation demo — locked to MySQL
        System.out.println("--- Violation ---");
        BadUserService bad = new BadUserService();
        bad.saveUser("Alice");
        // want MongoDB? edit BadUserService source — no other way ❌

        // ✅ Correct demo — same UserService, three databases
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
 * ❌ VIOLATION: high-level class hardcodes a concrete low-level class
 * ===================================================================== */
class BadMySQLDatabase {
    void save(String data) {
        System.out.println("Saving to MySQL : " + data);
    }
}

class BadUserService {
    // ❌ hardcoded — UserService is glued to MySQL forever
    private final BadMySQLDatabase db = new BadMySQLDatabase();

    void saveUser(String name) {
        db.save(name);
    }
}

/* =====================================================================
 * ✅ CORRECT: depend on an abstraction; inject the concrete from outside
 * ===================================================================== */

// Step 1 — the abstraction (the contract)
interface Database {
    void save(String data);
}

// Step 2 — low-level classes implement the contract
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

// Step 3 — high-level class depends ONLY on the interface
class UserService {
    private final Database db; // ✅ knows only about Database — nothing specific

    // dependency is INJECTED from outside, not created inside
    UserService(Database db) {
        this.db = db;
    }

    void saveUser(String name) {
        db.save(name); // ✅ calls the contract — doesn't care who answers
    }
}
