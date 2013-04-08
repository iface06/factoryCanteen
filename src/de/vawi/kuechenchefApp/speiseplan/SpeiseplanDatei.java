/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.dateien.Datei;
import java.util.Iterator;

/**
 * 
 * @author Tatsch
 * @version 30.01.2013
 */
class SpeiseplanDatei implements Datei{

    private Kantine kantine;

    public SpeiseplanDatei(Kantine kantine) {
        this.kantine = kantine;
    }
    
    @Override
    public String getDateiname() {
        return "exportDateien/speiseplan_" + kantine.name() + ".txt";
    }

    @Override
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
