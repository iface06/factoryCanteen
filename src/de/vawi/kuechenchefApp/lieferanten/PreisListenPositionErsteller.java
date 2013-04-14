package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.entities.Lieferant;
import de.vawi.kuechenchefApp.entities.Nahrungsmittel;
import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.dateien.Parse;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.*;

/**
 * Diese Klasse erstellt die Preislisten-Positionen eines Lieferanten aus den
 * Zeilen der Preisliste.
 *
 * @author Struebe
 * @version 27.01.2013
 */
class PreisListenPositionErsteller {

    public static final int ABSCHNITT_GEBINDEGROESSE = 0;
    public static final int ABSCHNITT_EINHEIT = 1;
    public static final int ABSCHNITT_NAHRUNGSMITTELNAME = 2;
    public static final int ABSCHNITT_NAHRUNGSMITTELKATEGORIE = 3;
    public static final int ABSCHNITT_PREIS = 4;
    public static final int ABSCHNITT_VORRAT = 5;
    private final Lieferant lieferant;
    private NahrungsmittelVerwaltung nahrungsmittelVerwaltung = NahrungsmittelVerwaltung.getInstanz();

    PreisListenPositionErsteller(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

    /**
     * Diese Methode zerlegt die eingelesenen Dateien in einzelne Zeilen, und
     * separiert aus den Zeilen die benötigten Werte, wie Name des
     * Nahrungsmittels, Einheit, etc.
     *
     * @param preisListenPositionsZeile Eine Zeile der einzulesenden
     * Preislisten-datei, die nicht die Lieferanten-Zeile (sprich: erste Zeile
     * der Liste) ist.
     * @param lieferant Lieferant, dessen Preisliste eingelsen wird.
     * @throws InvalidPropertiesFormatException Da für die
     * Nahrungsmittel-Kategorie und die Einheit Kürzel aus der Datei ins
     * Programm übersetzt werden, wird hier eine Exception geworfen, sobald die
     * Kürzel dem Programm nicht bekannt sind.
     *
     */
    public PreisListenPosition erstelle(String preisListenPositionsZeile) throws FehlerBeimErstellenEinerPreislistenPosition {
        List<String> zellen = separiereZeile(preisListenPositionsZeile);
        return erstellePreisListenPosition(zellen);

    }

    /**
     * Diese Methode teilt die Preislisten-Positions-Zeilen (sprich Zeile 2 bis
     * n der Preisliste) in ihre Abschnitte, die durch Kommata getrennt sind.
     *
     * @param preisListenPositionsZeile Eine Zeile der Preisliste, die nicht die
     * Lieferanten-Zeile ist (Zeilen 2 bis n der Liste).
     * @return Gibt die einzelnen Abschnitte der Zeile wider.
     */
    private List<String> separiereZeile(String preisListenPositionsZeile) {
        CsvZeileSeparator csvSepp = new CsvZeileSeparator();
        List<String> preisListenPositionsAbschnitte = csvSepp.separiere(preisListenPositionsZeile);
        return preisListenPositionsAbschnitte;
    }

    /**
     * Diese Methode erstellt ein Objekt der Klasse PreislistenPosition anhand
     * der Preislisten-Positions-Zeilen (sprich: Zeilen 2 bis n der Preisliste).
     *
     * @param abschnitte die durch Kommata getrennten Abschnitte einer
     * Preislisten-Positions-Zeile.
     * @return ein Objekt der Klasse PreislistenPosition
     * @throws
     * de.vawi.kuechenchefApp.lieferanten.PreisListenPositionErsteller.FehlerBeimErstellenEinerPreislistenPosition
     * Kann eine Zeile nicht gelesen werden (bspw. weil der Syntax nicht stimmt)
     * wird diese Exception geworfen. Das Programm wird nicht beendet, sonder
     * läuft weiter.
     */
    private PreisListenPosition erstellePreisListenPosition(List<String> abschnitte) throws FehlerBeimErstellenEinerPreislistenPosition {
        PreisListenPosition preisListenPosition = new PreisListenPosition();
        try {
            preisListenPosition.setGebindeGroesse(parseStringToDouble(abschnitte.get(ABSCHNITT_GEBINDEGROESSE)));
            preisListenPosition.setNahrungsmittel(findeNahrungsmittel(abschnitte));
            preisListenPosition.setPreis(parseStringToDouble(abschnitte.get(ABSCHNITT_PREIS)));
            preisListenPosition.setVorratsBestand(parseStringToInteger(abschnitte.get(ABSCHNITT_VORRAT)));
            preisListenPosition.setNahrungsmittel(addiereVerfuegbareMenge(abschnitte, parseStringToInteger(abschnitte.get(ABSCHNITT_VORRAT)), parseStringToInteger(abschnitte.get(ABSCHNITT_GEBINDEGROESSE))));
            preisListenPosition.setLieferant(lieferant);
        } catch (Exception e) {
            throw new FehlerBeimErstellenEinerPreislistenPosition(lieferant, abschnitte);
        }

        return preisListenPosition;
    }

    /**
     * Wandelt Strings in Doubles um.
     *
     * @param value String, der in einen double-Wert umgewandelt werden soll.
     * @return double-Wert
     */
    private double parseStringToDouble(String value) {
        return Parse.toDouble(value);
    }

    /**
     * Wandelt Strings in Integers um.
     *
     * @param value String, der in einen integer-Wert umgewandelt werden soll.
     * @return int-Wert
     */
    private int parseStringToInteger(String value) {
        return Parse.toInteger(value);
    }

    /**
     * Diese Methode überprüft, ob das Nahrungsmittel, das in einer
     * Preislisten-Position aufgelistet wird, bereits existiert. Falls nicht,
     * wird es neu angelegt und ausgegeben.
     *
     * @param abschnitte Auflistung der Abschnitte einer Preislistenposition.
     * @return Gibt das Nahrungsmittel wider, das entweder gefunden, oder neu
     * angelegt wurde.
     */
    private Nahrungsmittel findeNahrungsmittel(List<String> abschnitte) {
        Nahrungsmittel nahrungsmittel;
        try {
            nahrungsmittel = nahrungsmittelVerwaltung.findeDurchName(abschnitte.get(ABSCHNITT_NAHRUNGSMITTELNAME));
        } catch (NahrungsmittelVerwaltung.NahrungsmittelNichtGefunden ex) {
            nahrungsmittel = erstelleNahrungsmittelWennNichtGefunden(abschnitte);
        }



        return nahrungsmittel;
    }

    /**
     * Diese Methode erstellt ein neues Nahrungsmittel anhand der in der Liste
     * angegebenen Auflistung von Abschnitten einer Preislistenposition.
     *
     * @param abschnitte Auflistung der Abschnitte einer PreislitenPosition.
     * @return Gibt das neue erstellte Nahrungsmittel wider.
     */
    private Nahrungsmittel erstelleNeuesNahrungsmittel(List<String> abschnitte) {
        NahrungsmittelErsteller ersteller = new NahrungsmittelErsteller();
        Nahrungsmittel nahrungsmittel = ersteller.erstelle(abschnitte);
        return nahrungsmittel;
    }

    /**
     * Diese Methode sucht aus der Preislistenposition das Nahrungsmittel und
     * berechnet die verfügbare Menge desselben, indem sie die verfügbare Menge
     * der einzelnen Lieferanten aufkummuliert.
     *
     * @param abschnitte Auflistung der Abschnitte einer PreislitenPosition.
     * @param vorratsBestand der Vorratsbestand eines Nahrungsmittels bei einem
     * Lieferanten.
     * @param gebindeGroesse die Gebindegröße, in der das Nahrungsmittel beim
     * Lieferanten verkauft wird.
     * @return Gibt da Nahrungsmittel wider, dessen Menge aufkummuliert wurde.
     */
    private Nahrungsmittel addiereVerfuegbareMenge(List<String> abschnitte, int vorratsBestand, int gebindeGroesse) {

        Nahrungsmittel nahrungsmittel = nahrungsmittelVerwaltung.findeDurchName(abschnitte.get(ABSCHNITT_NAHRUNGSMITTELNAME));
        nahrungsmittel.setVerfuegbareGesamtMenge(nahrungsmittel.getVerfuegbareGesamtMenge() + (vorratsBestand * gebindeGroesse));
        return nahrungsmittel;
    }

    /**
     * Diese Methode erstellt ein Nahrungsmittel, falls es bei einem vorherigen
     * Suchlauf nicht gefunden wurde, aber in einer Preislistenposition
     * aufgeführt wird.
     *
     * @param abschnitte die Abschnitte einer Preislisten-Position.
     * @return Gibt das neu erstelle Nahrungsmittel wieder.
     */
    protected Nahrungsmittel erstelleNahrungsmittelWennNichtGefunden(List<String> abschnitte) {
        Nahrungsmittel nahrungsmittel = erstelleNeuesNahrungsmittel(abschnitte);
        nahrungsmittelVerwaltung.fuegeHinzu(nahrungsmittel);
        return nahrungsmittel;
    }

    /**
     * Gibt eine RuntimeException aus, wenn ein Fehler beim Erstellen einer
     * Preislisten Position aufgetreten ist.
     */
    public static class FehlerBeimErstellenEinerPreislistenPosition extends RuntimeException {

        Lieferant lieferant;
        List<String> abschnitte;

        public FehlerBeimErstellenEinerPreislistenPosition(Lieferant lieferant, List<String> abschnitte) {
            this.lieferant = lieferant;
            this.abschnitte = abschnitte;
        }
    }
}
