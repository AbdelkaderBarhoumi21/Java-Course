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
