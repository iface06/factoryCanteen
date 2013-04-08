package de.vawi.kuechenchefApp.kostenaufstellung;

import java.util.List;


/**
 *
 * @author Tatsch
 * @version 30.01.2013
 */
public class KostenUebersicht {
    private double gesamtKosten;
    private double lieferKostenGesamt;
    private List<Kostenaufstellung> kostenaufstellungenProLieferant;

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
     * @return Gibt Kostenaufstellung pro Lieferanten zurück
     */
    public List<Kostenaufstellung> getKostenaufstellungenProLieferant() {
        return kostenaufstellungenProLieferant;
    }

    /**
     * 
     *  
     * @param kostenaufstellungenProLieferant
     */
    public void setKostenaufstellungenProLieferant(List<Kostenaufstellung> kostenaufstellungenProLieferant) {
        this.kostenaufstellungenProLieferant = kostenaufstellungenProLieferant;
    }
}
