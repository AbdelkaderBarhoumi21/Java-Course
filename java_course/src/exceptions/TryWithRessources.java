package exceptions;

import java.util.Scanner;

/**
 * try-with-resources: automatically closes the resource at the end of the
 * block (even if an exception is thrown), so you don't need a finally to
 * call close() manually.
 *
 * Used for resources like Scanner, files, DB connections, sockets â€” anything
 * that implements AutoCloseable. Prevents resource leaks.
 */
public class TryWithRessources {

    public static void main(String[] args) {
        // scanner.close() is called automatically when the try block ends
        try (var scanner = new Scanner(System.in)) {
            System.out.print("Enter a number: ");
            int n = scanner.nextInt();
            System.out.println("You entered: " + n);
        } catch (Exception e) {
            System.out.println("Input error");
        }

    }

}
