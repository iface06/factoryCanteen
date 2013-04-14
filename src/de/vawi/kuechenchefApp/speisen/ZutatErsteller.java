

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.entities.Nahrungsmittel;
import de.vawi.kuechenchefApp.entities.Zutat;
import de.vawi.kuechenchefApp.dateien.*;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.List;

/**
 * Diese Klasse erstellt Zutaten.
 * 
 * @author Tatsch
 * @version 29.01.2013
 */
class ZutatErsteller {
    public static final int MENGE = 1;
    public static final int NAHRUNGSMITTEL_NAME = 3;
    NahrungsmittelVerwaltung nahrungsmittels = NahrungsmittelVerwaltung.getInstanz();
    /**
     * Diese Methode erstellt eine Zuzaz aus einer Zeile der rezepte-Datei.
     * @param rezeptZeile Eine Zeile der Rezepte-Datei.
     * @return Gibt die neu erstellte Zutat wieder.
     */
    protected Zutat erstelle(String rezeptZeile){
        List<String> zellen = separiereRezeptZeile(rezeptZeile);
        Zutat zutat = erstelleZutat(zellen);
        
        return zutat;
    }

    private List<String> separiereRezeptZeile(String rezeptZeile) {
        CsvZeileSeparator separator = new CsvZeileSeparator();
        List<String> zellen = separator.separiere(rezeptZeile);
        return zellen;
    }

    private Zutat erstelleZutat(List<String> zellen) throws NumberFormatException {
        Zutat zutat = new Zutat();
        zutat.setMenge(Parse.toDouble(zellen.get(MENGE)));
        Nahrungsmittel nahrungsmittel = findeNahrungsmittelAusRezept(zellen.get(NAHRUNGSMITTEL_NAME));
        zutat.setNahrungsmittel(nahrungsmittel);
        return zutat;
    }
    
    /**
     * Findet anahnd des Namen eines Nahrungsmittels das Nahrungsmittel in einem Rezept
     * @param nahrungsmittelName Name des Nahrungsmittels
     * @return das gefundene Nahrungsmittel
     */
    protected Nahrungsmittel findeNahrungsmittelAusRezept(String nahrungsmittelName) {
        Nahrungsmittel nahrungsmittel = nahrungsmittels.findeDurchName(nahrungsmittelName);
        return nahrungsmittel;
    }
    
}
