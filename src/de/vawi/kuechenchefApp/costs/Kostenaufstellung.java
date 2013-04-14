package de.vawi.kuechenchefApp.costs;

import de.vawi.kuechenchefApp.purchaseList.PurchaseListPosition;
import de.vawi.kuechenchefApp.entities.Supplier;
import java.util.*;

/**
 * Diese Klasse enthält alle errechneten Kosten zu jedem Nahrungsmittel und die
 * Gesamtkosten über alle Nahrungsmittel einer Planungserperiode von 3 Wochen.
 *
 * @author Struebe
 * @version 20.01.2013
 */
public class Kostenaufstellung {

    Supplier lieferant;
    List<PurchaseListPosition> einkaufslistenPositionen;

    /**
     * Gibt Lieferant zurück
     * @return lieferant
     */
    public Supplier getLieferant() {
        return this.lieferant;
    }

    /**
     *
     * @param lieferant
     */
    public void setLieferant(Supplier lieferant) {
        this.lieferant = lieferant;
    }

    /**
     *
     * Gibt Einkaufslistenpositionen zurück
     * 
     * @return einkaufslistePositionen
     */
    public List<PurchaseListPosition> getEinkaufslistenPositionsListe() {
        return this.einkaufslistenPositionen;
    }

    /**
     *
     * @param einkaufslistenPositionen
     */
    public void setEinkaufslistenPositionsListe(List<PurchaseListPosition> einkaufslistenPositionen) {
        this.einkaufslistenPositionen = einkaufslistenPositionen;
    }

    /**
     * Diese Methode berechnet die Gesamtkosten (= Einkaufskoten + Lieferkosten)
     * eines Leiferanten.
     *
     * @return Gibt die Gesamtkosten für einen Lieferanten wider.
     */
    public double berechneGesamtKostenProLieferant() {
        double gesamtKostenProLieferant = 0.0;
        gesamtKostenProLieferant = berechneEinkaufsKostenProLieferant() + berechneLieferKostenProLieferant();
        return gesamtKostenProLieferant;
    }

    /**
     * Diese Methode berechnet die Einkaufskosten (= nur die Kosten für alle
     * dort gekauften Nahrungsmittel) eines Leiferanten.
     *
     * @return Gibt die Einkaufskosten für einen Lieferanten wider.
     */
    public double berechneEinkaufsKostenProLieferant() {
        double einkaufsKosten = 0.0;

        for (PurchaseListPosition position : einkaufslistenPositionen) {
            einkaufsKosten += position.getPreis();
        }
        return einkaufsKosten;
    }

    /**
     * Diese Methode berechnet die Lieferkosten eines Lieferanten.
     *
     * @return Gibt die Lieferkosten eines Lieferanten wider.
     */
    public double berechneLieferKostenProLieferant() {
        double lieferKosten = 0.0;
        lieferKosten = lieferant.berechneLieferkosten(berechneEinkaufsKostenProLieferant());
        return lieferKosten;
    }
}
