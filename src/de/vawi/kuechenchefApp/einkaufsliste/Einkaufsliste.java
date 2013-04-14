package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.entities.Lieferant;
import de.vawi.kuechenchefApp.entities.Nahrungsmittel;
import java.util.*;

/**
 * Diese Klasse verwaltet die Einkaufsliste.
 *
 * @author Lepping
 * @version 29.01.2013
 */
public class Einkaufsliste implements Iterable<EinkaufslistenPosition> {

    private List<EinkaufslistenPosition> positionen = new ArrayList<EinkaufslistenPosition>();

    /**
     * Fügt eine Positione zu der Einkaufsliste hinzu.
     *
     * @param position Position der Einkaufsliste
     */
    public void hinzufügenEinkaufslistenPosition(EinkaufslistenPosition position) {
        positionen.add(position);
    }

    /**
     * 
     * Gibt die positionen der Einkaufsliste zurück
     * 
     * @return Positionen der Einkaufsliste
     */
    public List<EinkaufslistenPosition> getPositionen() {
        return positionen;
    }


    /**
     *
     * Findet Position durch Nahrungsmittel
     * @param nahrungsmittel
     * @return position
     */
    protected EinkaufslistenPosition findePositionDurchNahrungsmittel(Nahrungsmittel nahrungsmittel) {
        for (EinkaufslistenPosition position : positionen) {
            if (position.getName().equals(nahrungsmittel.getName())) {
                return position;
            }
        }

        EinkaufslistenPosition position = new EinkaufslistenPosition(nahrungsmittel);
        hinzufügenEinkaufslistenPosition(position);
        return position;

    }

    /**
     *
     * @return Iterator über die Positionen der Einkaufsliste
     */
    @Override
    public Iterator<EinkaufslistenPosition> iterator() {
        return positionen.iterator();
    }


    /**
     *
     * Liefert Liste an Lieferanten bei denen bestellt wird zurück
     * 
     * @return Liste an Lieferanten (bei denen mindestens eine Zutat bestellt wird)
     */
    public Set<Lieferant> holeLieferanten() {
        Set<Lieferant> lieferanten = new HashSet<>();
        for (EinkaufslistenPosition position : positionen) {
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
    public List<EinkaufslistenPosition> holeEinkaufslistenpositionenVonLieferant(Lieferant lieferant) {
        List<EinkaufslistenPosition> positionenZuLieferant = new ArrayList<>();
        for (EinkaufslistenPosition position : this.positionen) {
            if (position.getLieferant().equals(lieferant)) {
                positionenZuLieferant.add(position);
            }
        }

        return positionenZuLieferant;
    }
}
