package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.entities.Speise;
import java.util.*;

/**
 * Diese Klasse fügt zu den Speise-Objekten die Beliebtheit der Speise hinzu.
 *
 * @author Tatsch
 * @version 28.01.2013
 */
class SpeisenErsteller {

    public static final int BELIEBTHEIT = 0;
    public static final int SPEISENNAME = 1;

    /**
     * Diese Methode übergibt die Abschnitte einer Zeilte der Hitliste an eine
     * Ersteller-Methode, die aus diesen eine Speise erstellt.
     *
     * @param hitlisteZeile eine Zeile der Hitliste.
     * @return Gibt eine Speise wider, die einen Namen und eine Beliebtheit hat.
     */
    Speise erstelle(String hitlisteZeile) {
        List<String> cells = separiereHitlisteZeile(hitlisteZeile);
        Speise speise = erstelleSpeise(cells);

        return speise;
    }

    /**
     * Diese Methode unterteilt eine Zeile der Hitliste in ihre durch Kommata
     * getrennten Abschnitte (hier: cells).
     *
     * @param hitlisteZeile eine Zeile aus der Datei hitliste.csv.
     * @return Gibt die Abschnitte (cells) der Zeile aus.
     */
    private List<String> separiereHitlisteZeile(String hitlisteZeile) {
        CsvZeileSeparator separator = new CsvZeileSeparator();
        List<String> cells = separator.separiere(hitlisteZeile);
        return cells;
    }

    /**
     * Diese Methode stellt aus den in der Hitliste gegebenen Informationen
     * (Belibtheit der Speise und Name der Speise) ein Objekt Speise.
     *
     * @param cells die durch Kommata getrennten Abschnitte einer Zeile der
     * Datei hitliste.csv
     * @return Gibt eine Speise samt Name und Beliebtheit wider.
     * @throws NumberFormatException Wirft diese Exception wenn die Zahl, die
     * die Beliebthit angibt, nicht gelesen weren kann.
     */
    private Speise erstelleSpeise(List<String> cells) throws NumberFormatException {
        Speise speise = new Speise();
        speise.setBeliebtheit(Integer.valueOf(cells.get(BELIEBTHEIT)));
        speise.setName(cells.get(SPEISENNAME));
        return speise;
    }
}
