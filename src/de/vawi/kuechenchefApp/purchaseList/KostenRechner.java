package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.entities.Supplier;
import java.util.List;


/**
 *
 * Berechnet die Kosten für die Einkaufsliste
 * @author Lepping
 * @version 29.01.2013
 */
class KostenRechner {

    /**
     * 
     * Gibt Gesamtkosten zurück
     * 
     * @param liste
     * @return Gesamtkosten
     */
    public double berechneGesamtkosten(PurchaseList liste){
        return berechneGesamtkostenFuerNahrungsmittel(liste) + berechneGesamtkostenFuerLieferung(liste);
    }
    
    /**
     * Gibt Gesamtkosten für alle Nahrungsmittel zurück
     * 
     * @param einkaufsliste
     * @return Gesamtkosten für Nahrungsmittel
     */
    public double berechneGesamtkostenFuerNahrungsmittel(PurchaseList einkaufsliste) {
        return berechneGesamtkostenFuerNahrungsmittel(einkaufsliste.getPositionen());
    }

    /**
     * 
     * Gibt Lieferkosten zurück
     * 
     * @param einkaufsliste
     * @return lieferkosten
     */
    public double berechneGesamtkostenFuerLieferung(PurchaseList einkaufsliste) {
        double lieferkosten = 0.0;
        for (Supplier lieferant : einkaufsliste.holeLieferanten()) {
            List<PurchaseListPosition> positionen = einkaufsliste.holeEinkaufslistenpositionenVonLieferant(lieferant);
            double nahrungsmittelKosten = berechneGesamtkostenFuerNahrungsmittel(positionen);
            lieferkosten += lieferant.berechneLieferkosten(nahrungsmittelKosten);
        }
        return lieferkosten;
    }

    private double berechneGesamtkostenFuerNahrungsmittel(List<PurchaseListPosition> positionen) {
        double einkaufsKosten = 0.0;

        for (PurchaseListPosition position : positionen) {
            einkaufsKosten += position.getPreis();
        }
        
        return einkaufsKosten;
    }

}
