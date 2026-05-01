package exceptions;

public class CustomExcpetion {

    public static void main(String[] args) {
        Compte compte = new Compte();
        try {
            compte.retirer(500);
        } catch (SoldeInsuffisantException e) {
            System.out.println(e.getMessage());
            System.out.println("Il manque : " + e.getMontantManquant() + "â‚¬");
        }
    }
}

class SoldeInsuffisantException extends Exception {
    private double montantDemande;
    private double soldeDisponible;

    public SoldeInsuffisantException(double montantDemande, double soldeDisponible) {

        super("Solde insuffisant : demandÃ© " + montantDemande +
                "â‚¬, disponible " + soldeDisponible + "â‚¬");
        this.montantDemande = montantDemande;
        this.soldeDisponible = soldeDisponible;
    }

    public double getMontantManquant() {
        return montantDemande - soldeDisponible;
    }

}

class Compte {
    private double solde;

    public void retirer(double montant) throws SoldeInsuffisantException {
        if (montant > solde) {
            throw new SoldeInsuffisantException(montant, solde);
        }

        solde -= montant;
    }
}
