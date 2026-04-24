package arrays;


public class arrays_3d {

    public static void main(String[] args) {

        // 3x3 matrix
        int[][] matrice = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        // Access
        int centre = matrice[1][1]; // 5
        System.out.println("Center: " + centre);

        // Iterate
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }

    }
}
