package de.vawi.kuechenchefApp.kostenaufstellung;

import de.vawi.kuechenchefApp.einkaufsliste.*;
import de.vawi.kuechenchefApp.entities.Lieferant;
import java.util.*;

/**
 * Erstellt anhand einer Einkaufsliste die entsprechende Kostenaufstellung.
 *
 * @author Struebe
 * @version 27.01.2013
 */
public class KostenaufstellungErsteller {

    private Einkaufsliste liste;
    private KostenUebersicht uebersicht;

    /**
     * Setzt die Einkaufsliste. Diese ist die Basis für die Berechnung der
     * Kostenaufstellung.
     *
     * @param liste Die zu bearbeitende Einkaufsliste.
     */
    public void setEinkaufsliste(Einkaufsliste liste) {
        this.liste = liste;
    }

    /**
     *
     * Gibt Kostenuebersicht zurück
     * 
     * @return uebersicht
     */
    public KostenUebersicht erstelle(){
        uebersicht = new KostenUebersicht();
        kostenaufstellungNachLieferant();
        berechneGesamtKosten();
        return uebersicht;
    }
    
    /**
     * Diese Methode berchnet die Gesamtkosten, indem sie die Kosten jedes
     * Lieferanten kumuliert.
     *
     * @return Gibt den Wert der Gesamtkosten wider.
     */
    public void berechneGesamtKosten() {
        double gesamtKosten = 0.0;
        double lieferKosten = 0.0;
        for (Kostenaufstellung kostenaufstellung : uebersicht.getKostenaufstellungenProLieferant()) {
            gesamtKosten += kostenaufstellung.berechneGesamtKostenProLieferant();
            lieferKosten += kostenaufstellung.berechneLieferKostenProLieferant();
            
        }
        uebersicht.setGesamtKosten(gesamtKosten);
        uebersicht.setLieferKostenGesamt(lieferKosten);
    }

    /**
     * Diese Methode filtert die Liste der Gesamtkosten nach Lieferanten, so
     * dass nur die Kosten eines bestimmten Lieferanten ausgegeben werden.
     *
     * @param lieferant der Lieferant, nach dem gefiltert werden soll.
     * @return Gibt die Kostenaufstellung für den gewünschten Lieferanten wider.
     */
    private Kostenaufstellung filtereNachLieferanten(Lieferant lieferant) {
        List<EinkaufslistenPosition> lieferantenFilter = new ArrayList<>();
        for (EinkaufslistenPosition position : liste.getPositionen()) {
            if (position.getLieferant().equals(lieferant)) {
                lieferantenFilter.add(position);
            }
        }
        Kostenaufstellung kostenaufstellungProLieferanten = new Kostenaufstellung();
        kostenaufstellungProLieferanten.setLieferant(lieferant);
        kostenaufstellungProLieferanten.setEinkaufslistenPositionsListe(lieferantenFilter);
        return kostenaufstellungProLieferanten;
    }

    /**
     * Diese Methode erstellt aus der Kostenaufstellung pro Lieferant eine
     * Kostenaufstellung aller Lieferanten, nach Lieferanten sortiert.
     *
     * @return Gibt eine Kostenaufstellung, die nach Lieferant sortiert ist
     * wider.
     */
    private void kostenaufstellungNachLieferant() {
        List<Kostenaufstellung> kostenaufstellungNachLieferant = new ArrayList<>();
        List<Lieferant> alleBeteiligtenLieferanten = zaehleLieferantenAuf();
        int j = alleBeteiligtenLieferanten.size();
        for (int i = 0; i < j; i++) {
            Lieferant lieferant = alleBeteiligtenLieferanten.get(i);
            Kostenaufstellung temporäreSpeicherverschwendendeKostenaufstellungDamitSonjaEsVersteht = filtereNachLieferanten(lieferant);
            kostenaufstellungNachLieferant.add(temporäreSpeicherverschwendendeKostenaufstellungDamitSonjaEsVersteht);
        }
        uebersicht.setKostenaufstellungenProLieferant(kostenaufstellungNachLieferant);   
    }

    /**
     * Diese Methode listet alle Lieferanten, bei denen eingekauft wird, auf.
     *
     * @return Gibt eine Liste aller Lieferanten, bei denen eingekauft wird,
     * wider
     */
    private List<Lieferant> zaehleLieferantenAuf() {
        List<Lieferant> lieferantenAuflistung = new ArrayList<>();
        for (EinkaufslistenPosition position : liste.getPositionen()) {
            Lieferant lieferant = position.getLieferant();
            if (!lieferantenAuflistung.contains(lieferant)) {
                lieferantenAuflistung.add(lieferant);
            }
        }

        return lieferantenAuflistung;
    }
}