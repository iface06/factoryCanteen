package de.vawi.kuechenchefApp.nahrungsmittel;

/**
 * Mögliche Einheiten aus den Importdateien.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public enum Einheit {

    STUECK(""), LITER("l"), GRAMM("g");
    private String abkuerzung;

    private Einheit(String abkuerzung) {
        this.abkuerzung = abkuerzung;
    }

    /**
     *
     * @return Gibt die Abkürzung wieder, die aus der Datei für die Einheit
     * eingelesen wird.
     */
    public String getAbkuerzung() {
        return abkuerzung;
    }

    /**
     * Diese Methode übersetzt die in der Datei angegebene Abkürzung einer
     * Einheit in eine vom Programm vorgegebene Einheit.
     *
     * @param abkuerzung die Abkürzung, die aus der Datei gelesen wird.
     * @return Gibt die vom Programm vorgegebene Einheit wider.
     */
    public static Einheit nachAbkuerzung(String abkuerzung) {
        for (Einheit einheit : values()) {
            if (einheit.getAbkuerzung().equals(abkuerzung)) {
                return einheit;
            }
        }
        return Einheit.STUECK;
    }
}
