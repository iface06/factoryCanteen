package de.vawi.kuechenchefApp.kostenaufstellung;

import de.vawi.kuechenchefApp.dateien.Datei;
import java.util.Iterator;


/**
 *
 * Erweitert Klasse Datei. 
 * 
 * @author Tatsch
 * @version 30.01.2013
 */
public class KostenUebersichtDatei implements Datei{

    @Override
    public String getDateiname() {
        return "exportDateien/kostenUebersicht.txt";
    }

    @Override
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
