package de.vawi.kuechenchefApp.entities;

/**
 * Mögliche Einheiten aus den Importdateien.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public enum Unit {

    STUECK(""), LITER("l"), GRAMM("g");
    private String abkuerzung;

    private Unit(String abkuerzung) {
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
    public static Unit nachAbkuerzung(String abkuerzung) {
        for (Unit einheit : values()) {
            if (einheit.getAbkuerzung().equals(abkuerzung)) {
                return einheit;
            }
        }
        return Unit.STUECK;
    }
}
