package design_patterns.observer;

import java.util.ArrayList;
import java.util.List;

/*
 * Observer Pattern
 * ----------------
 * Notifies many "observers" automatically when a "subject" changes.
 *
 *   Subject (BoutiqueEnLigne)
 *      │ keeps a list of
 *      ▼
 *   Observer (Observateur)
 *      ├── EmailObserver
 *      ├── PushObserver
 *      └── ... add as many as you want, no Subject change ✅
 *
 * When to use:
 *   - One change must trigger many reactions (events, UI updates, logs).
 *   - You want subscribers to come and go without touching the publisher.
 */
public class ObserverExample {

    public static void main(String[] args) {
        BoutiqueEnLigne boutique = new BoutiqueEnLigne();

        // Two observers subscribed via lambdas
        boutique.abonner((event, data) -> System.out.println("Email sent for: " + data));
        boutique.abonner((event, data) -> System.out.println("Push notification: " + data));

        boutique.ajouterProduit("Java 21 Book");
    }
}

interface Observateur {
    void mettreAJour(String evenement, Object donnee);
}

class BoutiqueEnLigne {
    private final List<Observateur> observateurs = new ArrayList<>();

    public void abonner(Observateur o) {
        observateurs.add(o);
    }

    public void desabonner(Observateur o) {
        observateurs.remove(o);
    }

    private void notifier(String evenement, Object donnee) {
        observateurs.forEach(o -> o.mettreAJour(evenement, donnee));
    }

    public void ajouterProduit(String produit) {
        System.out.println("New product added: " + produit);
        notifier("NEW_PRODUCT", produit);
    }
}
