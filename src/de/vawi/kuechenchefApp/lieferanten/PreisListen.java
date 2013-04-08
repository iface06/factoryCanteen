package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.*;
import java.io.*;
import java.util.*;

class PreisListen implements Iterable<PreisListe> {

    private List<PreisListe> preisListen = new ArrayList<>();

    /**
     * Diese Methode liest die Preislisten-Dateien ein.
     *
     * @param ordner Ordner, in dem die Preislisten-Dateien abgelegt sind.
     */
    void leseDateien(String ordner) {
        File preisListenOrdner = oeffneOrdner(ordner);
        List<String> dateiNamen = leseDateiNamenAusOrdner(preisListenOrdner);
        for (String dateiName : dateiNamen) {
            Datei preisliste = leseDatei(dateiName);
            preisListen.add(new PreisListe(preisliste));
        }
    }

    /**
     *
     * @return Ein Iterator-Objekt, das die Preislisten durchiteriert.
     */
    public Iterator<PreisListe> iterator() {
        return preisListen.iterator();
    }

    /**
     * Öffnet den gewünschten Ordner.
     *
     * @param ordner Pfad zu dem Ordner, in dem die Dateien liegen.
     * @return Gibt den Ordner, in dem die Datei geöffntet wurde als File
     * wieder.
     */
    protected File oeffneOrdner(String ordner) {
        File preisListenOrdner = new File(ordner);
        return preisListenOrdner;
    }

    /**
     * Diese Methode liest die Datei-Namen aus dem gewünschten Ordner aus.
     *
     * @param preisListenOrdner Der Ordner, in dem die Dateien gespeichert sind.
     * @return eine Lsite der Datei-Pfade.
     */
    protected List<String> leseDateiNamenAusOrdner(File preisListenOrdner) {
        List<String> dateiNamen = new ArrayList<String>();
        File[] preisListenDateien = preisListenOrdner.listFiles(new PreisListenDateiFilter());
        for (File file : preisListenDateien) {
            dateiNamen.add(file.getAbsolutePath());
        }
        return dateiNamen;
    }

    /**
     * Liest die Datei ein.
     *
     * @param dateiName Name der Datei
     * @return Gibt ein Objekt der Klasse Datei aus.
     */
    protected Datei leseDatei(String dateiName) {
        DateiLeser leser = new DateiLeser(dateiName);
        return leser.leseDatei();
    }

    /**
     * Überprüft, ob die Dateien im Ordner dem standard einer Preislisten-Datei
     * entsprechen, und filtert diejenigen heraus, die mit 'preisliste'
     * beginnen, und auf '.csv' enden.
     */
    class PreisListenDateiFilter implements FilenameFilter {

        @Override
        public boolean accept(File d, String name) {
            return name.toLowerCase().startsWith("preisliste") && name.toLowerCase().endsWith(".csv");
        }
    }
}
