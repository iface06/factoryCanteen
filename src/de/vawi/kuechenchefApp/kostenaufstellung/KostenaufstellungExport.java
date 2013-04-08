package de.vawi.kuechenchefApp.kostenaufstellung;

import de.vawi.kuechenchefApp.export.DateiExport;
import de.vawi.kuechenchefApp.dateien.DateiSchreiber;
import de.vawi.kuechenchefApp.kostenaufstellung.Kostenaufstellung;


/**
 * Diese Klasse ist für den Export der Kostenaufstellung verantwortlich. 
 * 
 * @author Lepping 
 * @version 30.01.2013
 */
public class KostenaufstellungExport extends DateiExport<KostenUebersicht>
{
    /**
     * Exportiert die Kostenaufstellung in eine Datei.
     * Dateiname: Kostenaufstellung.txt
     * 
     * @param  kostenUebersicht    Kostenaufstellung
     */
    public void export(KostenUebersicht kostenUebersicht) {
        String kosten = new String();
        KostenUebersichtDrucker drucker = new KostenUebersichtDrucker();
        kosten += drucker.drucke(kostenUebersicht);
        
        DateiSchreiber schreiber = new DateiSchreiber(new KostenUebersichtDatei());
        schreiber.schreibe(kosten);
    }
}
