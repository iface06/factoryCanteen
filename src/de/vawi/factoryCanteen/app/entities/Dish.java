package de.vawi.factoryCanteen.app.entities;

import java.io.Serializable;
import java.util.*;

/**
 * Speisen aus der Rezeptedatei. Erweitert um die Beliebtheit aus der Hitliste.
 *
 * @author Tatsch
 * @version 28.01.2013
 */
public class Dish implements Serializable {

    private String name;
    private Integer popularity;
    private DishCategory category;

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
     * Überprüft per Name, ob die Speise existiert.
     *
     * @param obj Objekt
     * @return Gibt per Wahrheitswert wieder, ob die Speise existiert.
     */
    @Override
    public boolean equals(Object obj) {
        Dish d = (Dish) obj;
        return d.name.equals(this.name);
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
    public DishCategory getCategory() {
        return category;
    }

    /**
     * setzt die Kategory der Speise (Vegetarisch, Fish, Fleich)
     *
     * @param category
     */
    public void setCategory(DishCategory category) {
        this.category = category;
    }
}
