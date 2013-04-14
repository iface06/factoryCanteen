
package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.PlanungsPeriode;
import de.vawi.kuechenchefApp.export.AsciiTable;
import de.vawi.kuechenchefApp.export.Row;
import java.util.*;

/**
 * Diese Klasse erstellt die String-Repraesentatin eines Speiseplans
 * @author Tatsch
 * @version 30.01.2013
 */
class SpeiseplanDrucker {

    private String ausdruck = new String();
    private PlanungsPeriode periode = new PlanungsPeriode();
    
    String drucke(Menu plan) {
        ausdruck += "Speiseplan";
        ausdruck += "\n\n";
        ausdruck += speiseplanTabellen(plan);
        
        return ausdruck;
    }
    
    private String speiseplanTabellen(Menu plan){
        int woche = 0;
        String tabellen = new String();
        while(woche < periode.getAnzahlWochen()){
            tabellen += ueberschriftWoche(woche);
            tabellen += speiseplanFuerEineWoche(plan, woche);
            tabellen += "\n\n\n";
            woche++;
        }
        return tabellen;
    }
    
    private String ueberschriftWoche(int woche) {
        String ueberschrift = "Woche " + (woche + 1);
        ueberschrift += "\n\n";
        return ueberschrift;
    }

    private String speiseplanFuerEineWoche(Menu plan, int woche) {
        AsciiTable tabelle = new AsciiTable();
        Row headRow = tabelle.row();
        Row variante1 = tabelle.row();
        Row variante2 = tabelle.row();
        Row variante3 = tabelle.row();
        
        
        int anzahlTage = 1;
        List<Tag> tage = plan.getTageMitGerichten();
        int ersterTag = woche * periode.getAnzahlTageProWoche();
        int letzterTag = ersterTag + periode.getAnzahlTageProWoche();
        for (int aktuellerTag = ersterTag; aktuellerTag < letzterTag; aktuellerTag++) {
            Tag tag = tage.get(aktuellerTag);
            headRow.cell("Tag " + anzahlTage);
            variante1.cell(tag.getBeliebtesteSpeise().getName());
            variante2.cell(tag.getZweitbeliebtesteSpeise().getName());
            variante3.cell(tag.getDrittbeliebtesteSpeise().getName());
            anzahlTage++;
        
        }
        return tabelle.writeTable();
    }
}
