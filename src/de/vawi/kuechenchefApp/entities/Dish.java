package de.vawi.kuechenchefApp.entities;

import java.io.Serializable;
import java.util.*;

/**
 * Speisen aus der Rezeptedatei. Erweitert um die Beliebtheit aus der Hitliste.
 *
 * @author Tatsch
 * @version 28.01.2013
 */
public class Dish implements Iterable<Ingredient>, Serializable{

    private String name;
    private int beliebtheit;
    private List<Ingredient> zutaten = new ArrayList<>();

    /**
     * @return Beliebtheit bei den Gästen
     */
    public int getBeliebtheit() {
        return beliebtheit;
    }

    /**
     * @param beliebtheit Beliebtheit bei den Gästen
     */
    public void setBeliebtheit(int beliebtheit) {
        this.beliebtheit = beliebtheit;
    }

    /**
     * @return Name des Speise
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Name der Speise
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Alle Zutaten einer Speise
     */
    public List<Ingredient> getZutaten() {
        return this.zutaten;
    }

    /**
     *
     * @param zutat Zutat einer Speise
     */
    public void addZutat(Ingredient zutat) {
        zutaten.add(zutat);
    }

    /**
     * Überprüft per Name, ob die Speise existiert.
     *
     * @param obj Objekt
     * @return Gibt per Wahrheitswert wieder, ob die Speise existiert.
     */
    @Override
    public boolean equals(Object obj) {
        Dish speise = (Dish) obj;
        return speise.name.equals(this.name);
    }

    /**
     *
     * @return Gibt einen Hash-Code für den Speisen-Namen wieder.
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Diese Methode geht die Zutaten einer Speise durch, um zu prüfen, welcher
     * Kategorie die Speise angehört
     *
     * @return Gibt die Kategorie der Speise wieder.
     */
    public FoodCategory getKategorie() {
        Iterator<Ingredient> iterator = zutaten.iterator();
        return iterator.hasNext() ? iterator.next().getCategory() : FoodCategory.VEGETARIAN;
    }

    /**
     *
     * @return Ein Iterator-Objekt, um die Zutaten zu iterieren.
     */
    @Override
    public Iterator<Ingredient> iterator() {
        return zutaten.iterator();
    }
}
