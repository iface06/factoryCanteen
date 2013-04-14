package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.entities.Supplier;
import de.vawi.kuechenchefApp.entities.Food;
import java.util.*;

/**
 * Diese Klasse verwaltet die Einkaufsliste.
 *
 * @author Lepping
 * @version 29.01.2013
 */
public class PurchaseList implements Iterable<PurchaseListPosition> {

    private List<PurchaseListPosition> positionen = new ArrayList<PurchaseListPosition>();

    /**
     * Fügt eine Positione zu der Einkaufsliste hinzu.
     *
     * @param position Position der Einkaufsliste
     */
    public void hinzufügenEinkaufslistenPosition(PurchaseListPosition position) {
        positionen.add(position);
    }

    /**
     * 
     * Gibt die positionen der Einkaufsliste zurück
     * 
     * @return Positionen der Einkaufsliste
     */
    public List<PurchaseListPosition> getPositionen() {
        return positionen;
    }


    /**
     *
     * Findet Position durch Nahrungsmittel
     * @param nahrungsmittel
     * @return position
     */
    protected PurchaseListPosition findePositionDurchNahrungsmittel(Food nahrungsmittel) {
        for (PurchaseListPosition position : positionen) {
            if (position.getName().equals(nahrungsmittel.getName())) {
                return position;
            }
        }

        PurchaseListPosition position = new PurchaseListPosition(nahrungsmittel);
        hinzufügenEinkaufslistenPosition(position);
        return position;

    }

    /**
     *
     * @return Iterator über die Positionen der Einkaufsliste
     */
    @Override
    public Iterator<PurchaseListPosition> iterator() {
        return positionen.iterator();
    }


    /**
     *
     * Liefert Liste an Lieferanten bei denen bestellt wird zurück
     * 
     * @return Liste an Lieferanten (bei denen mindestens eine Zutat bestellt wird)
     */
    public Set<Supplier> holeLieferanten() {
        Set<Supplier> lieferanten = new HashSet<>();
        for (PurchaseListPosition position : positionen) {
            lieferanten.add(position.getLieferant());
        }
        return lieferanten;
    }


    /**
     *
     * Liefert Liste an Einkaufslistenpositionen für einen bestimmten Lieferanten zurück
     * 
     * @param lieferant
     * @return Gibt die Liste an Einkaufslistenpositionen für einen bestimmten Lieferanten zurück
     */
    public List<PurchaseListPosition> holeEinkaufslistenpositionenVonLieferant(Supplier lieferant) {
        List<PurchaseListPosition> positionenZuLieferant = new ArrayList<>();
        for (PurchaseListPosition position : this.positionen) {
            if (position.getLieferant().equals(lieferant)) {
                positionenZuLieferant.add(position);
            }
        }

        return positionenZuLieferant;
    }
}
