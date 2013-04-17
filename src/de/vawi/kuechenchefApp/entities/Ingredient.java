package de.vawi.kuechenchefApp.entities;

/**
 * Diese Klasse repräsentiert eine Zutat eines Gerichtes. Dementsprechend hält
 * diese die Informationen über Art und Menge des Nahrungsmittels.
 *
 * @author Tatsch
 * @version 29.01.2013
 */
public class Ingredient {

    private double amount;
    private Food food;

    /**
     * @return Menge die für das Zubereiten benötigt wird.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param menge Menge die für das Zubereiten benötigt wird.
     */
    public void setAmount(double menge) {
        this.amount = menge;
    }

    /**
     * @return Das zugrundeliegende Nahrungsmittel
     */
    public Food getFood() {
        return food;
    }

    /**
     * @param nahrungsmittel Das zugrundeliegende Nahrungsmittel
     */
    public void setFood(Food nahrungsmittel) {
        this.food = nahrungsmittel;
    }
/**
 * 
 * @param obj Objekt
 * @return Gibt wieder, ob die Zutat mit einem Nahrungsmittel übereinstimmt.
 */
    @Override
    public boolean equals(Object obj) {
        Ingredient ingrediant = (Ingredient) obj;
        return food.equals(ingrediant.food);
    }
/**
 * 
 * @return gibt einen Hash-Code für das Nahrungsmittel aus.
 */
    @Override
    public int hashCode() {
        return food.hashCode();
    }
/**
 * 
 * @return Gibt die Kategorie für ein Nahrungsmittel aus.
 */
    public FoodCategory getCategory() {
        return food.getKategorie();
    }
/**
 * 
 * @return Gibt die Einheit für ein Nahrungsmittel aus.
 */
    public Unit getUnit() {
        return food.getEinheit();
    }
/**
 * 
 * @return Gibt den Namen eines Nahrungsmittels aus.
 */
    public String getName() {
        return food.getName();
    }
}
