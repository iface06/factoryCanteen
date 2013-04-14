package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.Datei;
import java.util.Iterator;

/**
 * Diese Klasse bearbeitet die Preislisten-Datei.
 *
 * @author Struebe
 * @version 20.01.2013
 */
class PreisListe implements Datei {

    private Datei datei;

    PreisListe(Datei datei) {
        this.datei = datei;
    }

    /**
     *
     * @return Name der Datei.
     */
    @Override
    public String getDateiname() {
        return datei.getDateiname();
    }

    /**
     * 
     * @return erste Zeile einer Preisliste
     */
    public String getLieferantenZeile() {
        Iterator<String> iterator = iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return "";

    }

    /**
     * 
     * @return Iterator ab Zeilen zwei
     */
    public Iterator<String> preisListenPositionenIterator() {
        Iterator<String> iterator = iterator();
        if (iterator.hasNext()) {
            iterator.next();
        }

        return iterator;
    }

    /**
     *
     * @return Gibt ein Iterator-Objekt wieder, das die Datei durchiteriert.
     */
    @Override
    public Iterator<String> iterator() {
        return datei.iterator();

    }
}
