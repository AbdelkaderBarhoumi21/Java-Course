package generic;

import java.util.ArrayList;
import java.util.List;

/*
 * Example 1 â€” Raw type (runtime error):
 *   try {
 *       List liste = new ArrayList();
 *       liste.add("Hello");
 *       liste.add(42);
 *       String s = (String) liste.get(1);   // ðŸ’¥ ClassCastException
 *   } catch (ClassCastException e) {
 *       e.printStackTrace();
 *   }
 *
 * Example 2 â€” Generic type (compile-time safe):
 *   try {
 *       List<String> liste = new ArrayList<>();
 *       liste.add("Hello");
 *       // liste.add(42);                   // âŒ compile error
 *       String s = liste.get(0);            // no cast needed
 *   } catch (Exception e) {
 *       e.printStackTrace();
 *   }
 */
public class GenericExample {
    public static void main(String[] args) {

        try {
            List liste = new ArrayList(); // Raw type â€” holds "Object"
            liste.add("Hello");
            liste.add(42); // Compiles fine â€” anything goes
            String s = (String) liste.get(1); // ðŸ’¥ ClassCastException at RUNTIME
            System.out.println(s);

        } catch (ClassCastException e) {
            System.err.println("Error is :" + e);
            // System.out.println("Error is :" + e.getMessage());
            e.printStackTrace();

        }

    }

}
