package arrays;

/**
 * Arrays in Java:
 * - Arrays in Java have a fixed size — once created, you cannot add or remove elements.
 * - Indexes start at 0.
 * - arr.length returns the size of the array (no parentheses, it is an attribute).
 */
public class arrays {

    public static void main(String[] args) throws Exception {

        // Declaration and initialization
        int[] numbers = {1, 2, 3, 4, 5};

        // Declaration with a fixed size
        int[] arr = new int[5]; // [0, 0, 0, 0, 0] — default values
        arr[0] = 100;

        int firstElement = arr[0];
        int secondElement = arr[1];

        for (int i = 0; i < arr.length; i++) {
            System.out.println("Index " + i + " : " + arr[i]);
        }

    }
}
