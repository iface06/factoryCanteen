package de.vawi.kuechenchefApp.foods;

import de.vawi.kuechenchefApp.entities.Food;
import java.util.HashSet;
import java.util.Set;

/**
 * Diese Klasse verwaltet die Nahrungsmittel.
 *
 * @author Struebe
 * @version 27.01.2013
 */
public class NahrungsmittelVerwaltung {

    public static NahrungsmittelVerwaltung INSTANZ;
    private Set<Food> nahrungsmittels = new HashSet<>();

    /**
     * Diese Methode überprüft anhand des Namens eines Nahrungsmittels, ob es
     * bereits existiert.
     *
     * @param name Name des Nahrungsmittels
     * @return Gibt das Nahrungsmittel wider, das schon existiert. Existiert
     * noch kein Nahrungsmittel mit diesem Namen, wird Null wider gegeben.
     */
    public Food findeDurchName(String name) throws NahrungsmittelNichtGefunden {
        for (Food nahrungsmittel1 : nahrungsmittels) {
            if (nahrungsmittel1.getName().contains(name)) {
                return nahrungsmittel1;
            }
        }
        throw new NahrungsmittelNichtGefunden();
    }

    /**
     * Diese Methode fügt ein neues Nahrungsmittel zu einem Hashset hinzu.
     *
     * @param nahrungsmittel ein Nahrungsmittel.
     */
    public void fuegeHinzu(Food nahrungsmittel) {
        nahrungsmittels.add(nahrungsmittel);
    }

    /**
     * Diese Methode stellt sicher, dass genau eine Instanz der
     * NahrungsmittelVerwaltung existiert.
     *
     * @return Gibt die Insatanz der NahrungsmittelVerwaltung wieder.
     */
    public static NahrungsmittelVerwaltung getInstanz() {
        if (INSTANZ == null) {
            INSTANZ = new NahrungsmittelVerwaltung();
        }
        return INSTANZ;

    }
    
    /**
     * Der Fehler der geworfen wird, wenn ein Nahrungsmittel nicht gefunden wird.
     */
    public class NahrungsmittelNichtGefunden extends RuntimeException {
    }
}
