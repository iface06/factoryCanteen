package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.dateien.DateiOrdnerSuche;
import de.vawi.kuechenchefApp.einkaufsliste.*;
import de.vawi.kuechenchefApp.kostenaufstellung.*;
import de.vawi.kuechenchefApp.lieferanten.PreisListenImport;
import de.vawi.kuechenchefApp.speisen.SpeisenImport;
import de.vawi.kuechenchefApp.speiseplan.*;

/**
 * Diese Klasse ist der Einstiegspunkt der Anwendung KüchenchefApp.
 *
 * @author Tatsch
 * @version 30.01.2013 
 *
 *
 */
public class KuechenchefApp {

    /**
     * Diese Methode ist der Einstiegspunkt der Anwendung KüchenchefApp.
     *
     * @param args Keine Argumente bislang notwendig!
     */
    public static void main(String[] args) throws Exception {
        String dateiOrdner = importDateienOrdnerAbfragen();
        if (dateiOrdner != null && !dateiOrdner.isEmpty()) {
            starteProgramm(dateiOrdner);
        } else {
            System.out.println("Kein Ordner für Importdateien ausgewählt! -> Programm beendet");
        }
        System.out.println("Die Speisepläne, Einkaufslisten und Kostenaufstellung befinden sich im Ordner \"exportDateien\"");
    }

    private static String importDateienOrdnerAbfragen() {
        String dateiOrdner = new DateiOrdnerSuche().dateiOrdnerSuche();
        return dateiOrdner;
    }

    private static void importiereDateien(String dateiOrdner) {
        importierePreisListen(dateiOrdner);
        importiereSpeisen(dateiOrdner);
    }

    private static void importiereSpeisen(String dateiOrdner) {
        new SpeisenImport(dateiOrdner).importFiles();
    }

    private static void importierePreisListen(String dateiOrdner) {
        new PreisListenImport(dateiOrdner).importFiles();
    }

    private static ProzessSteuerung erstelleProzessSteuerung() {
        ProzessSteuerung prozess = new ProzessSteuerung();
        prozess.setSpeiseplanErsteller(new SpeiseplanErsteller());
        prozess.setEinkaufslistenErsteller(new EinkaufslistenErsteller());
        prozess.setKostenaufstellungErsteller(new KostenaufstellungErsteller());
        return prozess;
    }

    /**
     * Startet den Import und startet die Prozesssteuerung
     * @param dateiOrdner
     */
    protected static void starteProgramm(String dateiOrdner) {
        importiereDateien(dateiOrdner);

        ProzessSteuerung steuerung = erstelleProzessSteuerung();
        steuerung.start();

    }
}
