package de.vawi.kuechenchefApp.entities;

import java.io.Serializable;
import java.util.*;

/**
 * Speisen aus der Rezeptedatei. Erweitert um die Beliebtheit aus der Hitliste.
 *
 * @author Tatsch
 * @version 28.01.2013
 */
public class Dish implements Iterable<Ingredient>, Serializable {

    private String name;
    private Integer popularity;
    private List<Ingredient> Ingredients = new ArrayList<>();

    /**
     * @return Beliebtheit bei den Gästen
     */
    public Integer getPopularity() {
        return popularity;
    }

    /**
     * @param beliebtheit Beliebtheit bei den Gästen
     */
    public void setPopularity(Integer beliebtheit) {
        this.popularity = beliebtheit;
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
    public List<Ingredient> getIngredients() {
        return this.Ingredients;
    }

    /**
     *
     * @param zutat Zutat einer Speise
     */
    public void addIngredient(Ingredient zutat) {
        Ingredients.add(zutat);
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
    public FoodCategory getCategory() {
        Iterator<Ingredient> iterator = Ingredients.iterator();
        return iterator.hasNext() ? iterator.next().getCategory() : FoodCategory.VEGETARIAN;
    }

    /**
     *
     * @return Ein Iterator-Objekt, um die Zutaten zu iterieren.
     */
    @Override
    public Iterator<Ingredient> iterator() {
        return Ingredients.iterator();
    }
}
