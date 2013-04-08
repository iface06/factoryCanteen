package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenPosition;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import java.util.*;

/**
 * Diese Klasse verwaltet die importierten Lieferanten.
 *
 * @author Struebe
 * @version 12.01.2013
 */
public class LieferantenVerwaltung {

    static LieferantenVerwaltung INSTANZ;
    private List<PreisListenPosition> preisListenPositionen = new ArrayList<>();

    LieferantenVerwaltung() {
    }

    /**
     * Diese Methode überprüft, ob ein Objekt der LieferantenVerwaltung
     * existiert. Wenn nicht wird eines erstellt.
     *
     * @return Gibt ein Objekt der Lieferantenverwaltung wider.
     */
    public static LieferantenVerwaltung getInstanz() {
        if (INSTANZ == null) {
            INSTANZ = new LieferantenVerwaltung();
        }
        return INSTANZ;
    }
    /**
     * 
     * @return Gibt die Anzahl der Preislistenpositionen wieder.
     */
    public int anzahlPreisListenPositionen(){
        return preisListenPositionen.size();
    }

    /**
     * Diese Methode stellt eine Liste an Preislistenpositionen eines
     * Lieferanten zusammen.
     *
     * @param positionen Eine Position auf der Liste.
     */
    public void hinzufuegenPreisListenPosition(List<PreisListenPosition> positionen) {
        preisListenPositionen.addAll(positionen);
    }

    /**
     * Mit dieser Methode werden alle Preislitenpositionen aufgelistet, die ein
     * bestimmtes Nahrungsmittel enthalten. So kann herausgefunden werden, bei
     * welchen Lieferanten die jeweiligen Nahrungsmittel inkl. ihrer Attribute
     * zur Verfügung stehen. Die Preislistenpositionen sind absteigend nach
     * Preisen sortiert.
     *
     * @param nahrungsmittel Ein Objekt vom Typ Nahrungsmittel.
     * @return Gibt eine Auflistung der Positionen mit dem gesuchten
     * Nahrungsmittel wider.
     */
    public List<PreisListenPosition> findeDurchNahrungsmittel(Nahrungsmittel nahrungsmittel) {
        List<PreisListenPosition> positionen = new ArrayList<>();
        for (PreisListenPosition position : preisListenPositionen) {
            if (position.getNahrungsmittel().equals(nahrungsmittel)) {
                positionen.add(position);
            }
        }
        sortierePreisListenPositionenNachPreis(positionen);
        return positionen;
    }

    /**
     * Diese Methode sortiert die Preislisten-Positionen nach Preis.
     *
     * @param positionen Die einzelnen Positionen auf einer
     * Preislisten-Positions-Liste.
     */
    private void sortierePreisListenPositionenNachPreis(List<PreisListenPosition> positionen) {
        Collections.sort(positionen, new VergleichePreisListenPositionNachPreisProEinheit());
    }
    
    /**
     * Diese Methode ermittelt zu einem Nahrungsmittel das entsprechende Angebot eine Lieferanten
     * @param nahrungsmittel Das Nahrungsmittel zu dem eine Angebot gefunden werden soll.
     * @param lieferant Der Lieferant bei welchem das Angebot ermittelt werden soll.
     * @return liefert eine entsprechende Preislistenposition zurueck, falls kein Angebot gefunden wurde null
     */
    public PreisListenPosition findeAngebotFuerNahrungsmittelVonLieferant(Nahrungsmittel nahrungsmittel, Lieferant lieferant) {
        for (PreisListenPosition position : preisListenPositionen) {
            if(position.getLieferant().equals(lieferant) && position.getNahrungsmittel().equals(nahrungsmittel)){
                return position;
            }
        }
        
        return null;
    }
    
    
}
