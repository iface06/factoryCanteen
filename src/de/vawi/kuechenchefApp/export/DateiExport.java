package de.vawi.kuechenchefApp.export;


/**
 * Gibt den Ablauf vor wie ein Exportant in eine Datei exportiert wird.
 * 
 * @author Lepping
 * @version 30.01.2013
 */
public abstract class DateiExport<T>
{

    /**
     * Führt den Export in die Datei aus. 
     * 
     * @param  exportant    Den zu exportierenden Exportant
     */
    public abstract void export(T exportant);
}
