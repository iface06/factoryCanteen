package de.vawi.kuechenchefApp.entities;

/**
 * Diese Klasse repräsentiert eine Zutat eines Gerichtes. Dementsprechend hält
 * diese die Informationen über Art und Menge des Nahrungsmittels.
 *
 * @author Tatsch
 * @version 29.01.2013
 */
public class Ingredient {

    private double menge;
    private Food nahrungsmittel;

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
    public Food getNahrungsmittel() {
        return nahrungsmittel;
    }

    /**
     * @param nahrungsmittel Das zugrundeliegende Nahrungsmittel
     */
    public void setNahrungsmittel(Food nahrungsmittel) {
        this.nahrungsmittel = nahrungsmittel;
    }
/**
 * 
 * @param obj Objekt
 * @return Gibt wieder, ob die Zutat mit einem Nahrungsmittel übereinstimmt.
 */
    @Override
    public boolean equals(Object obj) {
        Ingredient zutat = (Ingredient) obj;
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
    public FoodCategory getKategorie() {
        return nahrungsmittel.getKategorie();
    }
/**
 * 
 * @return Gibt die Einheit für ein Nahrungsmittel aus.
 */
    public Unit getEinheit() {
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
