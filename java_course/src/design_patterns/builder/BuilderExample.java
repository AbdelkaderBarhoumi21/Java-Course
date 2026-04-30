package design_patterns.builder;

import java.util.HashMap;
import java.util.Map;

/*
 * Builder Pattern
 * ---------------
 * Builds a complex object step-by-step using a fluent API,
 * instead of a giant constructor with many optional parameters.
 *
 *   new Requete.Builder(url)
 *       .methode("POST")
 *       .header(..., ...)
 *       .body(...)
 *       .build();   // → immutable Requete
 *
 * When to use:
 *   - The object has many optional fields.
 *   - You want immutability + readable construction.
 *   - You want to avoid "telescoping" constructors.
 *
 * Why every setter ends with `return this;`
 * -----------------------------------------
 * `this` = the current Builder instance the method was called on.
 * Returning it lets the NEXT call dot-chain onto the SAME Builder:
 *
 *   new Builder(url)  ──► Builder
 *      .methode(...)  ──► Builder  (same one, just modified)
 *      .header(...)   ──► Builder  (same one, just modified)
 *      .body(...)     ──► Builder  (same one, just modified)
 *      .build()       ──► Requete  (NEW immutable object)
 *
 * Without `return this;` the setter would return void and you'd have to
 * write each step on its own line:
 *
 *   Builder b = new Builder(url);
 *   b.methode("POST");   // void → can't chain
 *   b.header("X", "Y");
 *   b.body("...");
 *   Requete req = b.build();
 *
 * With `return this;` the same Builder flows through every call:
 *
 *   Builder b1 = new Builder(url);
 *   Builder b2 = b1.methode("POST");
 *   b1 == b2  →  true   (it's literally the same object)
 *
 * Only `build()` breaks the chain — it returns a new `Requete` built
 * from the Builder's collected fields.
 */
public class BuilderExample {

    public static void main(String[] args) {
        Requete req = new Requete.Builder("https://api.example.com/users")
                .methode("POST")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token123")
                .body("{\"nom\": \"Alice\"}")
                .build();

        System.out.println(req);
    }
}

class Requete {
    private final String url;
    private final String methode;
    private final Map<String, String> headers;
    private final String body;

    private Requete(Builder b) {
        this.url = b.url;
        this.methode = b.methode;
        this.headers = Map.copyOf(b.headers);
        this.body = b.body;
    }

    @Override
    public String toString() {
        return methode + " " + url + " headers=" + headers + " body=" + body;
    }

    public static class Builder {
        private final String url;                       // required
        private String methode = "GET";                  // optional, default GET
        private final Map<String, String> headers = new HashMap<>();
        private String body;

        public Builder(String url) {
            this.url = url;
        }

        public Builder methode(String methode) {
            this.methode = methode;
            return this;
        }

        public Builder header(String cle, String val) {
            headers.put(cle, val);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Requete build() {
            return new Requete(this);
        }
    }
}
