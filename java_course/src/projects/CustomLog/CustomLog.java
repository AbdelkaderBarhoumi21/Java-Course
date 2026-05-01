package projects.CustomLog;

import java.lang.annotation.*;
import java.lang.reflect.*;

/// => @interface is like a class but for annotations level and message looks
/// like methods @interface is like a class but for annotations. level() and
/// message() look like methods but they are actually parameters — the values you
/// pass when you use the annotation. Think of it like defining a label template
/// : Template : @Log[ level=? message=? ] If you skip a parameter that has a
/// default, @Log // no params → level="INFO", message="" (both defaults)
/// 
/// @Log does nothing by itself. It is just a label stuck on the method. It does
///      not print anything, does not log anything. It just sits there. The
///      logging only happens when you explicitly read it via reflection
///      : AnnotationReader.printLoggedMethods(UserService.class);

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Log {
    String level()

    default "Info";

    String message() default "";
}

class UserService{

    @Log(level = "INFO", message = "User created")
    public void createUser(String name) {
        System.out.println("User created: " + name);
    }

    @Log(level = "WARN", message = "Deleting user — irreversible")
    public void deleteUser(String name) {
        System.out.println("User deleted: " + name);
    }

    public void listUsers() {
        // No @Log — this method is not logged
        System.out.println("Listing all users");
    }

}

class AnnotationReader {
    static void printLoggedMethods(Class<?> clazz) {
        System.out.println("=== @Log methods in " + clazz.getSimpleName() + " ===");

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Log.class)) {
                Log log = method.getAnnotation(Log.class);
                System.out.println(
                        "[" + log.level() + "] " +
                                method.getName() + "() → " + log.message());
            }
        }

    }
}

public class CustomLog {
    public static void main(String[] args) {
        // Read all @Log annotations without running the methods
        AnnotationReader.printLoggedMethods(UserService.class);

        System.out.println();

        // Actually run the methods
        UserService service = new UserService();
        service.createUser("Alice");
        service.deleteUser("Bob");
        service.listUsers();
    }
}

// Output:
// === @Log methods in UserService ===
// [INFO] createUser() → Creating new user
// [WARN] deleteUser() → Deleting user — irreversible
//
// User created: Alice
// User deleted: Bob
// Listing all users