package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.export.DateiExport;
import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.dateien.DateiSchreiber;
import de.vawi.kuechenchefApp.speiseplan.Menu;
import java.util.*;
/**
 * Diese Klasse ist fuer den Export der Speisepläne verantwortlich.
 * 
 * @author Lepping 
 * @version 30.01.2013
 */
public class SpeiseplanExport extends DateiExport<List<Menu>>
{

   /**
    * Exportiert jeden Speiseplan in eine seperate Datei.
    * Name der Dateien: Speiseplan_[KANTINE].txt
    * Beispiel: Speiseplan_Essen.txt;
    * 
    * @param  exportants    Liste von Speisepläne
   */
   public void export(List<Menu> exportants){
       for (Menu speiseplan : exportants) {
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
    protected DateiSchreiber erstelleSchreiberFuer(Menu speiseplan) {
        return new DateiSchreiber(new SpeiseplanDatei(speiseplan.getKantine()));
    }
}
