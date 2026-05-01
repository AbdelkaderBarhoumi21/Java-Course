package exceptions;

/**
 * Exceptions: Exceptions are errors that occur at runtime. Java uses a
 * try-catch mechanism to handle them cleanly instead of letting the program
 * crash.
 *
 * Exception hierarchy:
 *
 * Throwable
 * â”œâ”€â”€ Error            <- System errors (OutOfMemoryError, StackOverflowError) â€” do NOT catch
 * â””â”€â”€ Exception        <- The GLOBAL parent of all catchable exceptions
 *     â”œâ”€â”€ IOException          <- Checked (compiler forces you to handle it)
 *     â”‚   â”œâ”€â”€ FileNotFoundException
 *     â”‚   â””â”€â”€ EOFException
 *     â”œâ”€â”€ SQLException         <- Checked (database errors)
 *     â””â”€â”€ RuntimeException     <- Unchecked (not required to handle)
 *         â”œâ”€â”€ NullPointerException           (accessing a null object)
 *         â”œâ”€â”€ ArrayIndexOutOfBoundsException (invalid array index)
 *         â”œâ”€â”€ ArithmeticException            (e.g. division by zero)
 *         â”œâ”€â”€ NumberFormatException          (bad string -> number conversion)
 *         â”œâ”€â”€ ClassCastException             (invalid type cast)
 *         â””â”€â”€ IllegalArgumentException       (invalid method argument)
 *
 * IMPORTANT â€” "Exception e" means ANY type of exception:
 *   Since every specific exception (like ArrayIndexOutOfBoundsException,
 *   NullPointerException, ArithmeticException, etc.) is a SUBTYPE of the
 *   global "Exception" class, writing "catch (Exception e)" acts as a
 *   catch-all: it will match ANY exception thrown in the try block.
 *   That's why it should always come LAST in a chain of catch blocks â€”
 *   otherwise it would swallow more specific ones before they can be caught.
 *
 * try-catch-finally:
 *   - try     : block that may throw an exception.
 *   - catch   : block that runs if the matching exception is thrown.
 *   - finally : block that ALWAYS runs, whether or not an exception occurred.
 *               Typically used to release resources (close a file, a
 *               connection, etc.).
 */
public class ExcpetionTree {

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
