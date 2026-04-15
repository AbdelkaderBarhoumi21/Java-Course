import java.util.Scanner;

public class App {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

      

        // Lire un String avec nextLine()
        System.out.print("Quel est ton nom ? ");
        String nom = scanner.nextLine();

        // Lire un int avec nextInt()
        System.out.print("Quel est ton âge ? ");
        int age = scanner.nextInt();

        // Vider le buffer après nextInt() (piège classique)
        scanner.nextLine();

        // Lire un autre String après un nextInt()
        System.out.print("Quelle est ta ville ? ");
        String ville = scanner.nextLine();

        // Lire un double avec nextDouble()
        System.out.print("Quelle est ta moyenne ? ");
        double moyenne = scanner.nextDouble();

        // Afficher les résultats
        System.out.println("\n--- Résultat ---");
        System.out.println("Nom : " + nom);
        System.out.println("Âge : " + age);
        System.out.println("Ville : " + ville);
        System.out.println("Moyenne : " + moyenne);
        scanner.close();
    }
}
