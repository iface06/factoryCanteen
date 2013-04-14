package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.entities.Unit;
import de.vawi.kuechenchefApp.entities.Food;
import de.vawi.kuechenchefApp.entities.Supplier;


/**
 * Postion für eine Einkaufsliste
 * 
 * @author Lepping
 * @version 29.01.2013
 */
public class PurchaseListPosition
{
    private double anzahlGebinde;
    private Food nahrungsmittel;
    private Supplier lieferant;
    private double preis;

    
    /**
     *
     * @param nahrungsmittel
     */
    public PurchaseListPosition(Food nahrungsmittel) {
        this.nahrungsmittel = nahrungsmittel;
    }

    
    
    /**
     * @return     Name des Lieferanten
     */
    public Supplier getLieferant() {
        return lieferant;
    }

    /**
     * @param  lieferant    Name des Lieferanten
     */
    public void setLieferant(Supplier lieferant) {
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
    public Unit getEinheit(){
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
    protected Food getNahrungsmittel() {
        return nahrungsmittel;
    }
}
