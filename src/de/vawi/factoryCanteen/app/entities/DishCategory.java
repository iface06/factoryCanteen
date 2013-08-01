package de.vawi.factoryCanteen.app.entities;

import java.util.*;

/**
 * Mögliche Kategorien denen ein Nahrungsmittel zugeordnet sein kann.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public enum DishCategory {

    MEAT("m"), FISH("f"), VEGETARIAN("v");
    private String abkuerzung;

    private DishCategory(String abkuerzung) {
        this.abkuerzung = abkuerzung;
    }

    /**
     *
     * @return Die Abkürzung die aus der Datei für die Speisen- und
     * Nahrungsmittel-Kategorie eingelesen wird.
     */
    public String getAbbrevation() {
        return abkuerzung;
    }

    /**
     * Diese Methode übersetzt die in der Datei angegebene Abkürzung einer
     * Speisen- und Nahrungsmittelkategorie in eine vom Programm vorgegebene
     * Einheit.
     *
     * @param abbr die Abkürzung, die aus der Datei gelesen wird.
     * @return Gibt die vom Programm vorgegebene Kategorie wider.
     */
    public static DishCategory byAbbrevation(String abbr) throws UnknownDishCategory {
        for (DishCategory kategorie : values()) {
            if (kategorie.abkuerzung.equals(abbr)) {
                return kategorie;
            }
        }
        throw new UnknownDishCategory();
    }
    
    public static class UnknownDishCategory extends RuntimeException{}
}
