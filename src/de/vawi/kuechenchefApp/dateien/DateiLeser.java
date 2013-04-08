package de.vawi.kuechenchefApp.dateien;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Diese Klasse ist für das einlesen der Dateien zuständig.
 *
 * @author Tatsch
 * @version 30.01.2013
 */
public class DateiLeser {

    protected String dateiName;
    private DateiLeserManager manager;

    /**
     *
     * @param dateiName Name der Datei
     */
    public DateiLeser(String dateiName) {
        this.dateiName = dateiName;
    }

    /**
     * Diese Methode erstellt aus den eingelesenen Zeilen einer Datei ein Objekt
     * vom Typ Datei.
     *
     * @return eine Datei aus den eingelesenen Zeilen
     */
    public Datei leseDatei() {
        manager = erstelleDateiManager();
        List<String> zeilen = leseZeilenAusDatei();
        Datei datei = erstelleDatei(zeilen);
        return datei;
    }
/**
 * 
 * @return Objekt der Klasse VawiDateiManager
 */
    protected VawiDateiManager erstelleDateiManager() {
        return new VawiDateiManager(dateiName);
    }

    private void oeffneDatei() throws IOException {
        manager.openInFile();
    }

    private List<String> leseAlleZeilenInDatei() throws IOException {
        List<String> zeilen = new ArrayList<>();
        while (!istEndeDerDateiErreicht()) {
            String zeile = leseNaechsteZeileEin();
            if (zeile != null) {
                zeilen.add(zeile);
            }
        }

        return zeilen;
    }

    private boolean istEndeDerDateiErreicht() {
        return manager.eof();
    }

    private String leseNaechsteZeileEin() throws IOException {
        return manager.readLine();
    }

    private void schliesseDatei() throws IOException {
        manager.closeInFile();
    }
/**
 * 
 * @param ex Gibt einen Log aus, wenn die IOException geworfen wird.
 */
    protected void behandelFehlerfall(IOException ex) {
        Logger.getLogger(DateiLeser.class.getName()).log(Level.SEVERE, null, ex);
    }

    private List<String> leseZeilenAusDatei() {
        List<String> zeilen = new ArrayList<>();
        try {
            oeffneDatei();
            zeilen = leseAlleZeilenInDatei();
            schliesseDatei();
        } catch (IOException ex) {
            behandelFehlerfall(ex);
        }
        return zeilen;
    }

    private Datei erstelleDatei(final List<String> zeilen) {
        Datei datei = new Datei() {
            @Override
            public String getDateiname() {
                return dateiName;
            }

            @Override
            public Iterator<String> iterator() {
                return zeilen.iterator();
            }
        };
        return datei;
    }
}
