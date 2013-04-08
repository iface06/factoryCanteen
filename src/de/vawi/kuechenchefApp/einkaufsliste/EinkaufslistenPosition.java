package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.lieferanten.Lieferant;


/**
 * Postion für eine Einkaufsliste
 * 
 * @author Lepping
 * @version 29.01.2013
 */
public class EinkaufslistenPosition
{
    private double anzahlGebinde;
    private Nahrungsmittel nahrungsmittel;
    private Lieferant lieferant;
    private double preis;

    
    /**
     *
     * @param nahrungsmittel
     */
    public EinkaufslistenPosition(Nahrungsmittel nahrungsmittel) {
        this.nahrungsmittel = nahrungsmittel;
    }

    
    
    /**
     * @return     Name des Lieferanten
     */
    public Lieferant getLieferant() {
        return lieferant;
    }

    /**
     * @param  lieferant    Name des Lieferanten
     */
    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

    /**
     * @return     anzahlGebinde des eingekauften Nahrungsmittel
     */
    public double getMenge() {
        return anzahlGebinde;
    }

    /**
     * @param  anzahlGebinde   Menge des eingekauften Nahrungsmittel
     */
    public void setMenge(double anzahlGebinde) {
        this.anzahlGebinde = anzahlGebinde;
    }

    /**
     * @return     Bezeichnung des Nahrungsmittels
     */
    public String getName() {
        return this.nahrungsmittel.getName();
    }
    
    /**
     * @return     Einheit der Menge zum Nahrungsmittel
     */
    public Einheit getEinheit(){
        return this.nahrungsmittel.getEinheit();
    }

    /**
     * @return     Preis für die Menge des Nahrungsmittels, exkl. Lieferkosten
     */
    public double getPreis() {
        return this.preis;
    }

    /**
     * @param  preis   Preis für die Menge des Nahrungsmittels, exkl. Lieferkosten
     */
    public void setPreis(double preis) {
        this.preis = preis;
    }

    /**
     * Gibt Nahrungsmittel zurück
     * 
     * @return Nahrungsmittel
     */
    protected Nahrungsmittel getNahrungsmittel() {
        return nahrungsmittel;
    }
}
