package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.lieferanten.Lieferant;
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
    public double berechneGesamtkosten(Einkaufsliste liste){
        return berechneGesamtkostenFuerNahrungsmittel(liste) + berechneGesamtkostenFuerLieferung(liste);
    }
    
    /**
     * Gibt Gesamtkosten für alle Nahrungsmittel zurück
     * 
     * @param einkaufsliste
     * @return Gesamtkosten für Nahrungsmittel
     */
    public double berechneGesamtkostenFuerNahrungsmittel(Einkaufsliste einkaufsliste) {
        return berechneGesamtkostenFuerNahrungsmittel(einkaufsliste.getPositionen());
    }

    /**
     * 
     * Gibt Lieferkosten zurück
     * 
     * @param einkaufsliste
     * @return lieferkosten
     */
    public double berechneGesamtkostenFuerLieferung(Einkaufsliste einkaufsliste) {
        double lieferkosten = 0.0;
        for (Lieferant lieferant : einkaufsliste.holeLieferanten()) {
            List<EinkaufslistenPosition> positionen = einkaufsliste.holeEinkaufslistenpositionenVonLieferant(lieferant);
            double nahrungsmittelKosten = berechneGesamtkostenFuerNahrungsmittel(positionen);
            lieferkosten += lieferant.berechneLieferkosten(nahrungsmittelKosten);
        }
        return lieferkosten;
    }

    private double berechneGesamtkostenFuerNahrungsmittel(List<EinkaufslistenPosition> positionen) {
        double einkaufsKosten = 0.0;

        for (EinkaufslistenPosition position : positionen) {
            einkaufsKosten += position.getPreis();
        }
        
        return einkaufsKosten;
    }

}
