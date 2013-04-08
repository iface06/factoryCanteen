package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.export.DateiExport;
import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.dateien.DateiSchreiber;
import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import java.util.*;
/**
 * Diese Klasse ist fuer den Export der Speisepläne verantwortlich.
 * 
 * @author Lepping 
 * @version 30.01.2013
 */
public class SpeiseplanExport extends DateiExport<List<Speiseplan>>
{

   /**
    * Exportiert jeden Speiseplan in eine seperate Datei.
    * Name der Dateien: Speiseplan_[KANTINE].txt
    * Beispiel: Speiseplan_Essen.txt;
    * 
    * @param  exportants    Liste von Speisepläne
   */
   public void export(List<Speiseplan> exportants){
       for (Speiseplan speiseplan : exportants) {
           SpeiseplanDrucker drucker = new SpeiseplanDrucker();
           String plan = drucker.drucke(speiseplan);
           DateiSchreiber schreiber = erstelleSchreiberFuer(speiseplan);
           schreiber.schreibe(plan);
       }
   }

    /**
     * Erstellt DateiSchreiber für Speiseplan
     * @param speiseplan
     * @return Dateischreiber
     */
    protected DateiSchreiber erstelleSchreiberFuer(Speiseplan speiseplan) {
        return new DateiSchreiber(new SpeiseplanDatei(speiseplan.getKantine()));
    }
}
