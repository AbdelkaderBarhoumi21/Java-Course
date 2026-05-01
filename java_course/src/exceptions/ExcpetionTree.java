package exceptions;

/**
 * Exceptions: Exceptions are errors that occur at runtime. Java uses a
 * try-catch mechanism to handle them cleanly instead of letting the program
 * crash.
 *
 * Exception hierarchy:
 *
 * Throwable
 * ├── Error            <- System errors (OutOfMemoryError, StackOverflowError) — do NOT catch
 * └── Exception        <- The GLOBAL parent of all catchable exceptions
 *     ├── IOException          <- Checked (compiler forces you to handle it)
 *     │   ├── FileNotFoundException
 *     │   └── EOFException
 *     ├── SQLException         <- Checked (database errors)
 *     └── RuntimeException     <- Unchecked (not required to handle)
 *         ├── NullPointerException           (accessing a null object)
 *         ├── ArrayIndexOutOfBoundsException (invalid array index)
 *         ├── ArithmeticException            (e.g. division by zero)
 *         ├── NumberFormatException          (bad string -> number conversion)
 *         ├── ClassCastException             (invalid type cast)
 *         └── IllegalArgumentException       (invalid method argument)
 *
 * IMPORTANT — "Exception e" means ANY type of exception:
 *   Since every specific exception (like ArrayIndexOutOfBoundsException,
 *   NullPointerException, ArithmeticException, etc.) is a SUBTYPE of the
 *   global "Exception" class, writing "catch (Exception e)" acts as a
 *   catch-all: it will match ANY exception thrown in the try block.
 *   That's why it should always come LAST in a chain of catch blocks —
 *   otherwise it would swallow more specific ones before they can be caught.
 *
 * try-catch-finally:
 *   - try     : block that may throw an exception.
 *   - catch   : block that runs if the matching exception is thrown.
 *   - finally : block that ALWAYS runs, whether or not an exception occurred.
 *               Typically used to release resources (close a file, a
 *               connection, etc.).
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

        try {
            String[] names = { "Ab", "Br" };
            System.out.println(names[5]); // ArrayIndexOutOfBoundsException
            int result = Integer.parseInt("ABC"); // NumberFormatException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Number format exception: " + e.getMessage());
        } catch (Exception e) {
            // "Exception e" catches ANY exception not handled above,
            // because every exception type is a subtype of Exception.
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
