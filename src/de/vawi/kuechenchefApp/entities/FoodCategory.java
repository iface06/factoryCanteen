package de.vawi.kuechenchefApp.entities;

import java.util.*;

/**
 * Mögliche Kategorien denen ein Nahrungsmittel zugeordnet sein kann.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public enum FoodCategory {

    MEAT("m"), FISH("f"), VEGETARIAN("");
    private String abkuerzung;

    private FoodCategory(String abkuerzung) {
        this.abkuerzung = abkuerzung;
    }

    /**
     *
     * @return Die Abkürzung die aus der Datei für die Speisen- und
     * Nahrungsmittel-Kategorie eingelesen wird.
     */
    public String getAbkuerzung() {
        return abkuerzung;
    }

    /**
     * Diese Methode übersetzt die in der Datei angegebene Abkürzung einer
     * Speisen- und Nahrungsmittelkategorie in eine vom Programm vorgegebene
     * Einheit.
     *
     * @param abkuerzung die Abkürzung, die aus der Datei gelesen wird.
     * @return Gibt die vom Programm vorgegebene Kategorie wider.
     */
    public static FoodCategory byAbbrevation(String abkuerzung) {
        for (FoodCategory kategorie : values()) {
            if (kategorie.getAbkuerzung().equals(abkuerzung)) {
                return kategorie;
            }
        }
        return FoodCategory.VEGETARIAN;
    }

    /**
     * Diese Methode gibt die Kategorien aus, die nicht eingegeben werden.
     *
     * @param kategorie eine Speisen- und Nahrungsmittel-Kategorie.
     * @return Die Kategorien, die nicht eingegeben wurden.
     */
    public static List<FoodCategory> getOtherOwnTo(FoodCategory kategorie) {
        List<FoodCategory> andereKategorien = new ArrayList<>();
        for (FoodCategory kategoriee : values()) {
            if (!kategoriee.equals(kategorie)) {
                andereKategorien.add(kategoriee);
            }
        }
        return andereKategorien;
    }
}
