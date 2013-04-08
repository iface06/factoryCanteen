package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;

/**
 * Diese Klasse repräsentiert eine Zutat eines Gerichtes. Dementsprechend hält
 * diese die Informationen über Art und Menge des Nahrungsmittels.
 *
 * @author Tatsch
 * @version 29.01.2013
 */
public class Zutat {

    private double menge;
    private Nahrungsmittel nahrungsmittel;

    /**
     * @return Menge die für das Zubereiten benötigt wird.
     */
    public double getMenge() {
        return menge;
    }

    /**
     * @param menge Menge die für das Zubereiten benötigt wird.
     */
    public void setMenge(double menge) {
        this.menge = menge;
    }

    /**
     * @return Das zugrundeliegende Nahrungsmittel
     */
    public Nahrungsmittel getNahrungsmittel() {
        return nahrungsmittel;
    }

    /**
     * @param nahrungsmittel Das zugrundeliegende Nahrungsmittel
     */
    public void setNahrungsmittel(Nahrungsmittel nahrungsmittel) {
        this.nahrungsmittel = nahrungsmittel;
    }
/**
 * 
 * @param obj Objekt
 * @return Gibt wieder, ob die Zutat mit einem Nahrungsmittel übereinstimmt.
 */
    @Override
    public boolean equals(Object obj) {
        Zutat zutat = (Zutat) obj;
        return nahrungsmittel.equals(zutat.nahrungsmittel);
    }
/**
 * 
 * @return gibt einen Hash-Code für das Nahrungsmittel aus.
 */
    @Override
    public int hashCode() {
        return nahrungsmittel.hashCode();
    }
/**
 * 
 * @return Gibt die Kategorie für ein Nahrungsmittel aus.
 */
    public SpeisenUndNahrungsmittelKategorie getKategorie() {
        return nahrungsmittel.getKategorie();
    }
/**
 * 
 * @return Gibt die Einheit für ein Nahrungsmittel aus.
 */
    public Einheit getEinheit() {
        return nahrungsmittel.getEinheit();
    }
/**
 * 
 * @return Gibt den Namen eines Nahrungsmittels aus.
 */
    public String getName() {
        return nahrungsmittel.getName();
    }
}
