package de.vawi.kuechenchefApp.entities;

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
    public static DishCategory byAbbrevation(String abbr) {
        for (DishCategory kategorie : values()) {
            if (kategorie.abkuerzung.equals(abbr)) {
                return kategorie;
            }
        }
        return DishCategory.VEGETARIAN;
    }

    /**
     * Diese Methode gibt die Kategorien aus, die nicht eingegeben werden.
     *
     * @param kategorie eine Speisen- und Nahrungsmittel-Kategorie.
     * @return Die Kategorien, die nicht eingegeben wurden.
     */
    public static List<DishCategory> getOtherOwnTo(DishCategory kategorie) {
        List<DishCategory> andereKategorien = new ArrayList<>();
        for (DishCategory kategoriee : values()) {
            if (!kategoriee.equals(kategorie)) {
                andereKategorien.add(kategoriee);
            }
        }
        return andereKategorien;
    }
}
