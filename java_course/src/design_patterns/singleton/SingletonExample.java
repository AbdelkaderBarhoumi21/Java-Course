package design_patterns.singleton;

import java.util.HashMap;
import java.util.Map;

/*
 * Singleton Pattern
 * -----------------
 * Guarantees a class has exactly ONE instance, with a global access point.
 *
 *   Configuration.getInstance().set("lang", "en");
 *
 * Thread-safe variant used here: double-checked locking with `volatile`.
 *
 * When to use:
 *   - Truly global, shared state (config, logger, cache).
 *   - You'd otherwise pass the same object everywhere.
 *
 * Watch out:
 *   - Hidden global state makes testing harder; prefer dependency injection
 *     when the shared object is just "convenient" rather than truly global.
 */
public class SingletonExample {

    public static void main(String[] args) {
        Configuration.getInstance().set("langue", "fr");
        String langue = Configuration.getInstance().get("langue");
        System.out.println("langue = " + langue);

        // Same instance every time
        System.out.println("same? " + (Configuration.getInstance() == Configuration.getInstance()));
    }
}

class Configuration {
    private static volatile Configuration instance;
    private final Map<String, String> parametres = new HashMap<>();

    private Configuration() {
        // private — no one outside can instantiate
    }

    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new Configuration();
                }
            }
        }
        return instance;
    }

    public String get(String cle) {
        return parametres.get(cle);
    }

    public void set(String cle, String valeur) {
        parametres.put(cle, valeur);
    }
}
