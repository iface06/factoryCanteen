package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.Parse;
import java.util.List;

/**
 * Diese Klasse bestimmt anhand der Preislisten-Datei den Namen und Typ des
 * Lieferanten.
 *
 * @author Struebe
 * @version 25.01.2013
 */
class LieferantenErsteller {

    private static final int LIEFERANTENTYP = 0;
    private static final int NAME = 1;
    private static final int LIEFERKOSTEN = 2;

    /**
     * Diese Methode bestimmt, ob ein Lieferant als Großhändler, oder als Bauer
     * angelegt wird.
     *
     * @param lieferantenZellen Die Zellen, aus der die Parameter für die
     * Lieferanten ausgelesen werden (sprich: die erste Zeile der Preisliste).
     * @return Gibt den typisierten Lieferanten mit Name und dem Lieferkosensatz
     * bzw. Entfernung wider.
     * @throws de.vawi.kuechenchefApp.dateien.Parse.FehlerBeimParsen Wird
     * geworfen, wenn aus einem Text kein Double generiert werden kann.
     */
    Lieferant erstelle(List<String> lieferantenZellen) throws Parse.FehlerBeimParsen {
        Lieferant lieferant = null;
        String typ = lieferantenZellen.get(LIEFERANTENTYP);
        String name = lieferantenZellen.get(NAME);
        String lieferkosten = lieferantenZellen.get(LIEFERKOSTEN);

        if (typ.equals("Grosshandel")) {
            lieferant = erstelleGroßhaendler(name, lieferkosten);
        } else if (typ.equals("Bauer")) {
            lieferant = erstelleBauer(name, lieferkosten);
        }

        return lieferant;
    }

    /**
     * Diese Methode erstellt einen Lieferanten des Typs Großhändler.
     *
     * @param name Name des Großhändlers.
     * @param lieferkosten Lieferkostensatz des Großhändlers.
     * @return Gibt einen Lieferanten vom Typ Großhändler wider.
     */
    private Lieferant erstelleGroßhaendler(String name, String lieferkosten) {
        Lieferant lieferant;
        lieferant = new Grosshaendler();
        lieferant.setName(name);
        lieferant.setLieferKostenFaktor(parseStringToDouble(lieferkosten));

        return lieferant;
    }

    /**
     * Diese Methode erstellt einen Lieferanten des Typs Bauer.
     *
     * @param name Name des Bauers.
     * @param lieferkosten Entfernung des Bauers in Kilometern.
     * @return Gibt einen Lieferanten vom Typ Bauern wider.
     */
    private Lieferant erstelleBauer(String name, String lieferkosten) {
        Lieferant lieferant;
        lieferant = new Bauer();
        lieferant.setName(name);
        lieferant.setLieferKostenFaktor(parseStringToDouble(lieferkosten));
        return lieferant;
    }

    /**
     * Diese Methode übergibt einen String-Wert in einen double-Wert.
     * @param doubleValue String, der in einen double übergeben werden soll.
     * @return Gibt den umgewandelten double-Wert wider.
     */
    private double parseStringToDouble(String doubleValue) {
        return Parse.toDouble(doubleValue);
    }
}
