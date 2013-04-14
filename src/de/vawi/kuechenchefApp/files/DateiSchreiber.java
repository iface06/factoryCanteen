/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.dateien;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beer
 * @version 30.01.2013
 */
public class DateiSchreiber {
    
    private DateiSchreiberManager dateiManager;
    private final Datei dateiName;
    

    /**
     *
     * Setzt Dateinamen
     * @param dateiName
     */
    public DateiSchreiber(Datei dateiName) {
        this.dateiName = dateiName;
        
    }

    /**
     * Schreibt String in Datei
     * @param inhalt
     */
    public void schreibe(String inhalt) {
        dateiManager = erzeugeDatei();
        oeffneDatei();
        schreiberInDatei(inhalt);
        schliesseDatei();
        
    }
    
    /**
     * Erzeugt neue Datei
     * @return
     */
    protected DateiSchreiberManager erzeugeDatei() {
        return new VawiDateiManager(dateiName.getDateiname());
    }

    private void oeffneDatei() {
        try {
            dateiManager.openOutFile();
        } catch (IOException ex) {
            Logger.getLogger(DateiSchreiber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void schreiberInDatei(String inhalt) {
        dateiManager.writeLine(inhalt);
    }

    private void schliesseDatei() {
        dateiManager.closeOutFile();
    }
}
