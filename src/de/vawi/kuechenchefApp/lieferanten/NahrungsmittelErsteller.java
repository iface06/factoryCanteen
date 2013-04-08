package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import java.util.List;

/**
 * Diese Klasse erstellt ein Nahrungsmittel durch
 *
 * @author Struebe
 * @version 25.01.2013
 */
class NahrungsmittelErsteller {

    public static final int ABSCHNITT_EINHEIT = 1;
    public static final int ABSCHNITT_NAHRUNGSMITTELNAME = 2;
    public static final int ABSCHNITT_NAHRUNGSMITTELKATEGORIE = 3;
    public static final int ABSCHNITT_VERFUEGBAREGESAMTMENGE = 6;

    /**
     * Diese Methode erstellt ein Objekt vom Typ Nahrungsmittel aus den
     * einzelnen Positionen auf einer Liste von Preislisten-Positionen.
     *
     * @param preisListenPositionen eine Liste an Preislisten-Positionen.
     * @return Gibt ein neu erstelltes Objekt vom Typ Nahrungsmittel wider.
     */
    public Nahrungsmittel erstelle(List<String> preisListenPositionen) {
        Nahrungsmittel nahrungsmittel = erstelleNahrungsmittel(preisListenPositionen);
        return nahrungsmittel;
    }

    /**
     * Diese Methode unterteilt eine Preislitenposition (= eine Zeile aus der
     * Preisliste) in ihre einzelnen, durch Kommata abgetrennten Abschnitte, und
     * weist den benötigten Abschnitten je ein Attribut des Nahrungsmittels zu.
     *
     * @param preislistenPositionen Auflistung der Preislistenpositionen;
     * entspricht der Preisliste.
     * @return Gibt ein Object vom Typ Nahrungsmittel mit den Attributen 'Name',
     * 'Einheit' und 'SpeisenUndNahrungsmittelKategorie' wider
     */
    private Nahrungsmittel erstelleNahrungsmittel(List<String> preislistenPositionen) {
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel();
        nahrungsmittel.setName(preislistenPositionen.get(ABSCHNITT_NAHRUNGSMITTELNAME));

        Einheit einheit = extrahiereEinheit(preislistenPositionen);
        nahrungsmittel.setEinheit(einheit);

        SpeisenUndNahrungsmittelKategorie kategorie = extrahiereKategorie(preislistenPositionen);
        nahrungsmittel.setKategorie(kategorie);

        return nahrungsmittel;
    }

    /**
     * Diese Methode weist der in der Preisliste gegebenen Abkürzung einer
     * Einheit einen Aufzähltyp aus dem Enum Einheit zu.
     *
     * @param preislistenPositionen Auflistung der Preislistenpositionen;
     * entspricht der Preisliste.
     * @return Gibt den Aufzähltypen des Enums Einheit wider.
     */
    private Einheit extrahiereEinheit(List<String> preislistenPositionen) {
        Einheit einheit = Einheit.nachAbkuerzung(preislistenPositionen.get(ABSCHNITT_EINHEIT));
        return einheit;
    }

    /**
     * Diese Methode weist der in der Preisliste gegebenen Abkürzung einer
     * Speisen- und Nahrungsmittelkategorie einen Aufzähltyp aus dem Enum
     * SpeisenUndNahrungsmittelKategorie zu.
     *
     * @param preislistenPositionen Auflistung der Preislistenpositionen;
     * entspricht der Preisliste.
     * @return Gibt den Aufzähltypen des Enums SpeisenUndNahrungsmittelKategorie
     * wider.
     */
    private SpeisenUndNahrungsmittelKategorie extrahiereKategorie(List<String> preislistenPositionen) {
        return SpeisenUndNahrungsmittelKategorie.nachAbkuerzung(preislistenPositionen.get(ABSCHNITT_NAHRUNGSMITTELKATEGORIE));
    }
}
