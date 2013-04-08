package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.speisen.Speise;
import java.util.*;
/**
 * Ein Speiseplan für eine Planungsperiode. Solch ein Plan ist einer Kantine zugeordnet und haelt eine Liste von Tagen
 * entsprechend der Anzahl von Tagen, die der Plan umfassen soll.
 * 
 * @author Beer 
 * @version 30.01.2013
 */
public class Speiseplan implements Iterable<Tag>
{
    private List<Tag> tageMitGerichten = new ArrayList<>();
    private Kantine kantine;
    
    /**
     * Konstruktor
     */
    public Speiseplan(){
        
    }
    
    /**
     * Konstruktor der die noetigen Parameter für einen Speiseplan aufnimmt.
     * @param kantine gibt die Kantine an zu dem der Plan erzeugt werden.
     * @param tageMitGerichten eine Liste mit Tagen, entsprechend der Anzahl an Tagen, die der Plan beinhalten soll
     */
    public Speiseplan(Kantine kantine, List<Tag> tageMitGerichten){
        this.kantine = kantine;
        this.tageMitGerichten = tageMitGerichten;
    }

    /**
     * @return  Gibt die die Liste mit den Tagen der Planungsperiode zurueck.
     */
    public List<Tag> getTageMitGerichten(){
        return this.tageMitGerichten;
    }  
    
    /**
     * @return  Gibt die zugeordnete Kantine zurueck
     */
    public Kantine getKantine(){
        return this.kantine;
    }
    
    /**
     * Setzt die zugehoerige Kantine
     * @param kantine 
     */
    public void setKantine(Kantine kantine) {
        this.kantine = kantine;
    }
    
    /**
     * Fuegt dem Plan einen Tag hinzu
     * @param tag 
     */
    public void fuegeTagHinzu(Tag tag){
        this.tageMitGerichten.add(tag);
    }
    
    /**
     * Macht einen Plan über die Tage iterierbar
     * @return 
     */
    @Override
    public Iterator<Tag> iterator() {
        return tageMitGerichten.iterator();
    }

}
