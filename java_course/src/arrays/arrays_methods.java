
import java.lang.reflect.Array;
import java.util.Arrays;

public class arrays_methods {

    public static void main(String[] args) {

        int[] num = {1, 2, 3, 4, 5};

        Arrays.sort(num);

        String text = Arrays.toString(num);
        System.out.println(text);

        int index = Arrays.binarySearch(num, 3);

        int[] copy = Arrays.copyOf(num, 2);

        Arrays.fill(num, 0);

        boolean equals = Arrays.equals(num, copy);

        System.out.println("Sorted: " + Arrays.toString(num));
        System.out.println("Index: " + index);
        System.out.println("Copy: " + Arrays.toString(copy));
        System.out.println("Filled: " + Arrays.toString(num));
        System.out.println("Equals: " + equals);

    }

}
