package de.vawi.kuechenchefApp.menues;


import java.util.*;
/**
 * Ein Speiseplan für eine Planungsperiode. Solch ein Plan ist einer Kantine zugeordnet und haelt eine Liste von Tagen
 * entsprechend der Anzahl von Tagen, die der Plan umfassen soll.
 * 
 * @author Beer 
 * @version 30.01.2013
 */
public class Menu implements Iterable<Day>
{
    private List<Day> tageMitGerichten = new ArrayList<>();
    private Canteen kantine;
    
    /**
     * Konstruktor
     */
    public Menu(){
        
    }
    
    /**
     * Konstruktor der die noetigen Parameter für einen Speiseplan aufnimmt.
     * @param kantine gibt die Kantine an zu dem der Plan erzeugt werden.
     * @param tageMitGerichten eine Liste mit Tagen, entsprechend der Anzahl an Tagen, die der Plan beinhalten soll
     */
    public Menu(Canteen kantine, List<Day> tageMitGerichten){
        this.kantine = kantine;
        this.tageMitGerichten = tageMitGerichten;
    }

    /**
     * @return  Gibt die die Liste mit den Tagen der Planungsperiode zurueck.
     */
    public List<Day> getTageMitGerichten(){
        return this.tageMitGerichten;
    }  
    
    /**
     * @return  Gibt die zugeordnete Kantine zurueck
     */
    public Canteen getKantine(){
        return this.kantine;
    }
    
    /**
     * Setzt die zugehoerige Kantine
     * @param kantine 
     */
    public void setKantine(Canteen kantine) {
        this.kantine = kantine;
    }
    
    /**
     * Fuegt dem Plan einen Tag hinzu
     * @param tag 
     */
    public void fuegeTagHinzu(Day tag){
        this.tageMitGerichten.add(tag);
    }
    
    /**
     * Macht einen Plan über die Tage iterierbar
     * @return 
     */
    @Override
    public Iterator<Day> iterator() {
        return tageMitGerichten.iterator();
    }

}
