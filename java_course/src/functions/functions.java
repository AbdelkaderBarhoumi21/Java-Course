package functions;

public class functions {

    // Méthode qui retourne une valeur
    public static int additionner(int a, int b) {
        return a + b;
    }

    // Méthode qui ne retourne rien (void)
    public static void afficherMessage(String message) {
        System.out.println(">>> " + message);
    }
    // Méthode avec valeur par défaut simulée (surcharge)

    public static double calculerTVA(double prix) {
        return calculerTVA(prix, 0.20);  // TVA par défaut : 20%
    }

    public static double calculerTVA(double prix, double taux) {
        return prix * (1 + taux);
    }

    public static void main(String[] args) {
        int result = additionner(5, 2);
        System.out.println("Résultat de l'addition : " + result);

        afficherMessage("Hello");

        double prixAvecTVA = calculerTVA(100);
        System.out.println("Prix avec TVA par défaut : " + prixAvecTVA);

        double prixAvecTVA15 = calculerTVA(100, 0.15);
        System.out.println("Prix avec TVA de 15% : " + prixAvecTVA15);
    }

}
