package de.vawi.kuechenchefApp.costs;

import java.util.List;


/**
 *
 * @author Tatsch
 * @version 30.01.2013
 */
public class CostReport {
    private double gesamtKosten;
    private double lieferKostenGesamt;
    private List<CostPosition> kostenaufstellungenProLieferant;

    /**
     * Gibt Gesamtkosten zurück
     * @return Gesamtkosten
     */
    public double getGesamtKosten() {
        return gesamtKosten;
    }

    /**
     * 
     * @param gesamtKosten
     */
    public void setGesamtKosten(double gesamtKosten) {
        this.gesamtKosten = gesamtKosten;
    }

    /**
     * 
     * Gibt gesamte Lieferkosten zurück
     * @return gesamte Lieferkosten
     */
    public double getLieferKostenGesamt() {
        return lieferKostenGesamt;
    }

    /**
     *
     * @param lieferKostenGesamt
     */
    public void setLieferKostenGesamt(double lieferKostenGesamt) {
        this.lieferKostenGesamt = lieferKostenGesamt;
    }

    /**
     *
     * @return Gibt CostPosition pro Lieferanten zurück
     */
    public List<CostPosition> getKostenaufstellungenProLieferant() {
        return kostenaufstellungenProLieferant;
    }

    /**
     * 
     *  
     * @param kostenaufstellungenProLieferant
     */
    public void setKostenaufstellungenProLieferant(List<CostPosition> kostenaufstellungenProLieferant) {
        this.kostenaufstellungenProLieferant = kostenaufstellungenProLieferant;
    }
}
