package de.vawi.kuechenchefApp.entities;

/**
 * Diese Klasse hält Informationen die für die Beschreibung eines
 * Nahrungsmittels benötigt werden.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class Food {

    private String name;
    private Unit einheit;
    private int verfuegbareGesamtMenge;
    private FoodCategory kategorie;



    /**
     * @return Einheit des Nahrungsmittels
     */
    public Unit getEinheit() {
        return einheit;
    }

    /**
     * @param einheit Einheit des Nahrungsmittels
     */
    public void setEinheit(Unit einheit) {
        this.einheit = einheit;
    }

    /**
     * @return Die Kategorie des Nahrungsmittels (Fleisch, Fisch, Vegetarisch)
     */
    public FoodCategory getKategorie() {
        return kategorie;
    }

    /**
     * @param kategorie Die Kategorie des Nahrungsmittels (Fleisch, Fisch,
     * Vegetarisch)
     */
    public void setKategorie(FoodCategory kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * @return Name des Nahrungsmittels
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Name des Nahrungsmittels inklusive seiner Kategorie.
     */
    public String toString() {
        return this.name + " (" + this.kategorie + ")";
    }

    /**
     * @param name Name des Nahrungsmittels
     */
    public void setName(String name) {
        this.name = name;
    }
/**
 * 
 * @return 
 */
    public int getVerfuegbareGesamtMenge() {
        return verfuegbareGesamtMenge;
    }
/**
 * 
 * @param verfuegbareGesamtMenge 
 */
    public void setVerfuegbareGesamtMenge(int verfuegbareGesamtMenge) {
        this.verfuegbareGesamtMenge = verfuegbareGesamtMenge;
    }


    /**
     *
     * @param obj Das zu vergleichende Objekt.
     * @return Gibt aus, ob das zu vergleichende Objekt ein Nahrungsmittel ist,
     * und einen Namen hat.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            Food nahrungsmittel = (Food) obj;
            return nahrungsmittel.name.equals(this.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
