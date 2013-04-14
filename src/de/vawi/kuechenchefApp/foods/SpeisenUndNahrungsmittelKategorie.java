package de.vawi.kuechenchefApp.nahrungsmittel;

import java.util.*;

/**
 * Mögliche Kategorien denen ein Nahrungsmittel zugeordnet sein kann.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public enum SpeisenUndNahrungsmittelKategorie {

    FLEISCH("m"), FISCH("f"), VEGETARISCH("");
    private String abkuerzung;

    private SpeisenUndNahrungsmittelKategorie(String abkuerzung) {
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
    public static SpeisenUndNahrungsmittelKategorie nachAbkuerzung(String abkuerzung) {
        for (SpeisenUndNahrungsmittelKategorie kategorie : values()) {
            if (kategorie.getAbkuerzung().equals(abkuerzung)) {
                return kategorie;
            }
        }
        return SpeisenUndNahrungsmittelKategorie.VEGETARISCH;
    }

    /**
     * Diese Methode gibt die Kategorien aus, die nicht eingegeben werden.
     *
     * @param kategorie eine Speisen- und Nahrungsmittel-Kategorie.
     * @return Die Kategorien, die nicht eingegeben wurden.
     */
    public static List<SpeisenUndNahrungsmittelKategorie> holeAndereKategorien(SpeisenUndNahrungsmittelKategorie kategorie) {
        List<SpeisenUndNahrungsmittelKategorie> andereKategorien = new ArrayList<>();
        for (SpeisenUndNahrungsmittelKategorie kategoriee : values()) {
            if (!kategoriee.equals(kategorie)) {
                andereKategorien.add(kategoriee);
            }
        }
        return andereKategorien;
    }
}
