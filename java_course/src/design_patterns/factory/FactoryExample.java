package design_patterns.factory;

/*
 * Factory Method Pattern
 * ----------------------
 * Hides "which concrete class to create" behind a single creator method.
 * Callers ask for a TYPE, not for `new XYZ()`.
 *
 *   Notification n = NotificationFactory.create("email");
 *   n.send("hello");
 *
 * When to use:
 *   - You pick a concrete implementation based on input/config.
 *   - You want to add new variants without touching call sites.
 */
public class FactoryExample {

    public static void main(String[] args) {
        Notification email = NotificationFactory.create("email");
        Notification sms   = NotificationFactory.create("sms");
        Notification push  = NotificationFactory.create("push");

        email.send("Welcome!");
        sms.send("Code: 1234");
        push.send("New message");
    }
}

interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    public void send(String message) {
        System.out.println("[EMAIL] " + message);
    }
}

class SmsNotification implements Notification {
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}

class PushNotification implements Notification {
    public void send(String message) {
        System.out.println("[PUSH] " + message);
    }
}

class NotificationFactory {
    public static Notification create(String type) {
        return switch (type.toLowerCase()) {
            case "email" -> new EmailNotification();
            case "sms"   -> new SmsNotification();
            case "push"  -> new PushNotification();
            default      -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}
