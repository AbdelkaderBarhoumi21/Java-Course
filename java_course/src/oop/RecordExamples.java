package oop;

/**
 * This class demonstrates Java Records, introduced in Java 14 (finalized in
 * Java 16).
 * 
 * A record is a special kind of class in Java that is designed to hold
 * immutable data.
 * It automatically generates:
 * - private final fields for each component
 * - a public constructor
 * - accessor methods (same name as the field, not getX())
 * - equals() and hashCode() based on all components
 * - toString() showing all components
 * 
 * Records are ideal for simple data carriers (DTOs, value objects).
 */
public class record_examples {
    public static void main(String[] args) {

        // -------------------------------------------------
        // Circle Record Example (basic record usage)
        // -------------------------------------------------
        Circle c = new Circle(3, 4);
        System.out.println("Circle x: " + c.x());
        System.out.println("Circle y: " + c.y());
        System.out.println("Circle toString: " + c);
        System.out.println("Circle equals: " + c.equals(new Circle(3, 4)));

        // -------------------------------------------------
        // Email Record Example (record with validation & custom method)
        // -------------------------------------------------
        System.out.println("\n--- Email Record Examples ---");

        // Valid email - automatically lowercased by the compact constructor
        Email validEmail = new Email("John.Doe@Example.COM");
        System.out.println("Stored email: " + validEmail.email());
        System.out.println("Domain: " + validEmail.domain());
        System.out.println("Email toString: " + validEmail);

        // Records automatically implement equals() based on their components
        Email sameEmail = new Email("john.doe@example.com");
        System.out.println("Emails equal? " + validEmail.equals(sameEmail));

        // Invalid email - will throw IllegalArgumentException
        try {
            Email invalidEmail = new Email("invalid-email");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        // -------------------------------------------------
        // Another valid email with different domain
        // -------------------------------------------------
        Email gmail = new Email("student@gmail.com");
        System.out.println("\nGmail domain: " + gmail.domain());
    }

}

// BEFORE — lots of repetitive boilerplate code
class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Point[x=" + x + ", y=" + y + "]";
    }
}

record Circle(int x, int y) {
}

record Email(String email) {

    public Email {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        email = email.toLowerCase();
    }

    public String domain() {
        return email.substring(email.indexOf('@') + 1);
    }
}
