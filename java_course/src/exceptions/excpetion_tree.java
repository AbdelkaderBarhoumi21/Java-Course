package exceptions;

/**
 * Exceptions: Exceptions are errors that occur at runtime. Java uses a
 * try-catch mechanism to handle them cleanly instead of letting the program
 * crash.
 *
 * Exception hierarchy:
 *
 * Throwable ├── Error <- System errors (OutOfMemoryError) — do NOT catch └──
 * Exception ├── IOException <- Checked (the compiler forces you to handle them)
 * ├── SQLException <- Checked └── RuntimeException <- Unchecked (not required
 * to handle) ├── NullPointerException ├── ArrayIndexOutOfBoundsException ├──
 * ArithmeticException └── IllegalArgumentException
 *
 * try-catch-finally: - try : block that may throw an exception. - catch : block
 * that runs if the matching exception is thrown. - finally : block that ALWAYS
 * runs, whether or not an exception occurred. Typically used to release
 * resources (close a file, a connection, etc.).
 */
public class excpetion_tree {

    public static int divide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Error: division by zero!");
            System.out.println("Message: " + e.getMessage());
            return 0;
        } finally {
            // ALWAYS runs, whether or not an exception was thrown
            System.out.println("Division operation finished.");
        }
    }

    public static void main(String[] args) {
        divide(10, 0);
        // Error: division by zero!
        // Message: / by zero
        // Division operation finished.
    }
}
