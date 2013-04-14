package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.entities.Lieferant;
import de.vawi.kuechenchefApp.dateien.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Diese Klasse importiert alle Lieferanten und ihre Angebote aus den
 * Preislistendateien.
 *
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class PreisListenImport {

    private PreisListen listen;
    private LieferantenVerwaltung lieferantenVerwaltung = LieferantenVerwaltung.getInstanz();

    String dateiOrdner;

    public PreisListenImport(String dateiOrdner) {
        this.dateiOrdner = dateiOrdner;
    }
    
    
    /**
     * Diese Methode importiert die Preislistendateien.
     *
     * Werden keine Preislisten-Dateien gefunden, so wird eine Exception
     * geworfen und das Programm anschließend beendet.
     *
     */
    public void importFiles() {
        try {
            lesePreisListenDateien();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode liest die Dateien aus dem angegebenen Ordner ein und listet
     * sie auf.
     *
     * @param ordner Hier wird beim Aufrufen der Methode der Pfad zum Ordner, wo
     * die Preislisten hinterlegt sind, als Parameter angegeben.
     * @return Gibt eine Auflistung der Lieferanten mit ihren jeweiligen
     * Preislisten-Positionen aus.
     * @throws Exception Wird geworfen, wenn Dateien gefunden werden, aber nicht
     * geöffnet werden können.
     */
    public void lesePreisListenDateien() throws Exception {
        listen = new PreisListen();
        listen.leseDateien(this.dateiOrdner);
        for (PreisListe preisListe : listen) {
            erstelleLieferantUndPreisListenPositionen(preisListe);
        }
    }
    private CsvZeileSeparator csvSepp = new CsvZeileSeparator();


    /**
     * Diese Methode erstellt einer Liste an Preislistenpositionen pro
     * Lieferant.
     *
     * @param preisListe
     *
     */
    private void erstelleLieferantUndPreisListenPositionen(PreisListe preisListe) {
        Lieferant lieferant = erstelleLieferant(preisListe);
        List<PreisListenPosition> positionen = erstellePreislistenPositionenZuLieferant(preisListe, lieferant);
        lieferantenVerwaltung.hinzufuegenPreisListenPosition(positionen);
    }

    /**
     * Diese Methode zieht den Lieferanten mitsamt Name und Typ aus der Datei.
     *
     * @param lieferantenZelle Die Zelle der Preisliste, in der der Lieferant zu
     * finden ist (sprich: die erste Zeile der Preisliste)
     * @return Gibt einen neuen Lieferanten mitsamt Name und Typ wider.
     */
    private Lieferant erstelleLieferantAusZellen(List<String> lieferantenZelle) {
        return new LieferantenErsteller().erstelle(lieferantenZelle);
    }

    /**
     * Diese Methode erstellt eine Liste mit Preislistenpositionen zum
     * entsprechenden Lieferanten.
     *
     * @param preisliste ein Object vom Typ Preisliste.
     * @param lieferant Ein Objekt vom Typ Lieferant.
     * @return Gibt eine fertige Liste an Preislistenpositionen eines
     * Lieferanten wider.
     */
    private List<PreisListenPosition> erstellePreislistenPositionenZuLieferant(PreisListe preisliste, Lieferant lieferant) {
        List<PreisListenPosition> positionen = new ArrayList<>();
        PreisListenPositionErsteller ersteller = new PreisListenPositionErsteller(lieferant);

        Iterator<String> positionszeilen = preisliste.preisListenPositionenIterator();
        while (positionszeilen.hasNext()) {
            String zeile = positionszeilen.next();
            try{
                PreisListenPosition position = ersteller.erstelle(zeile);
                positionen.add(position);                
            } catch (PreisListenPositionErsteller.FehlerBeimErstellenEinerPreislistenPosition ex){
                handleZeileKonnteNichtImportiertWerden(lieferant, zeile);
            }
        }

        return positionen;
    }


    /**
     * Diese Methode teilt die Lieferantenzeile (= 1. Zeile der Preisliste) in
     * ihre durch Kommata getrennten Abschnitte.
     *
     * @param lieferantenZeile die erste Zeile der Preisliste
     * @return Auflistung der einzelnen Abschnitte der Lieferantenzeile.
     */
    private List<String> separiereZeileInZellen(String lieferantenZeile) {
        List<String> lieferantenZellen = csvSepp.separiere(lieferantenZeile);
        return lieferantenZellen;
    }

    /**
     * Diese Methode liest den Lieferanten aus der Lieferantenzeile (=1. Zeile
     * der Preisliste).
     *
     * @param preisListe Ein Objekt der Klasse Preisliste.
     * @return Gibt ein Objekt der Klasse Lieferant wider.
     */
    private Lieferant erstelleLieferant(PreisListe preisListe) {
        String lieferantenZeile = preisListe.getLieferantenZeile();
        List<String> lieferantenZellen = separiereZeileInZellen(lieferantenZeile);
        Lieferant lieferant = erstelleLieferantAusZellen(lieferantenZellen);
        return lieferant;
    }

    protected void handleZeileKonnteNichtImportiertWerden(Lieferant lieferant, String zeile) { 
    }

    
}
