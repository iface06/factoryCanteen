package de.vawi.kuechenchefApp;

/**
 * 
 * Diese Klasse beinhaltet die Daten für die Planungsperiode.
 * Die Anzahl der Wochen, die Tage pro Woche und die Anzahl an Gerichten kann so zentral gepflegt werden.
 * 
 * @author Lepping
 * @version 29.01.2013
 */
public class Periode {
    
    private int anzahlWochen = 3;
    private int anzahlTageProWoche = 5;
    private int anzahlGerichteProTag = 3;

    /**
     *
     * @return Gibt die Anzahl an Wochen zurück
     */
    public int getAnzahlWochen() {
        return anzahlWochen;
    }

    /**
     *
     * @return Gibt die Anzahl an Tagen pro Woche zurück
     */
    public int getAnzahlTageProWoche() {
        return anzahlTageProWoche;
    }

    /**
     *
     * @return Gibt die Anzahl an Gerichten pro Tag zurück
     */
    public int getAnzahlGerichteProTag() {
        return anzahlGerichteProTag;
    }
    
    /**
     *
     * @return Gibt die Anzahl an benötigter Speisen für Planungsperiode zurück
     */
    public int berechneAnzahlBenoetigterSpeisen() {
       return anzahlGerichteProTag * anzahlTageProWoche * anzahlWochen;
    }

    
    
    /**
     * Setzt Anzahl der Wochen für eine Planungsperiode (Standart 3)
     * @param anzahlWochen
     */
    public void setAnzahlWochen(int anzahlWochen) {
        this.anzahlWochen = anzahlWochen;
    }

    
    /**
     * Setzt Anzahl der Tage die in eine Woche berücksichtigt werden sollen (Standart 5)
     * @param anzahlTageProWoche
     */
    public void setAnzahlTageProWoche(int anzahlTageProWoche) {
        this.anzahlTageProWoche = anzahlTageProWoche;
    }

    /**
     * Setzt Anzahl der angeboteten Gerichte pro Tag (Standart 3)
     * @param anzahlGerichteProTag
     */
    public void setAnzahlGerichteProTag(int anzahlGerichteProTag) {
        this.anzahlGerichteProTag = anzahlGerichteProTag;
    }

    
    
    /**
     *
     * Gibt Anzahl von benötigten Fischspeisen zurück
     * 
     * @return Anzahl an benötigten Fischpeisen
     */
    public int berechneAnzahlBenoetigterFischSpeisen() {
        return anzahlWochen;
    }
    
    /**
     *
     * Gibt Anzahl an benötigten vegetarischen Speisen zurück
     * 
     * @return Anzahl an benötigten vegetarischen Speisen
     */
    public int berechneAnzahlBenoetigteVegetarischeSpeisen() {
        return anzahlWochen * anzahlTageProWoche;
    }
    
    /**
     *
     * Gibt Anzahl an benötigten Fleischspeisen zurück
     * 
     * @return Anzahl an benötigten Fleischspeisen
     */
    public int berechneAnzahlBenoetigteFleischSpeisen() {
        return anzahlWochen * anzahlTageProWoche;
    }
    
}
