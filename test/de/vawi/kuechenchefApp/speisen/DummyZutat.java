/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;

/**
 *
 * @author Tatsch
 */
public class DummyZutat {

    private Zutat zutat = new Zutat();
    private Nahrungsmittel nahrungsmittel = new Nahrungsmittel();
    
    public DummyZutat name(String name){
        nahrungsmittel.setName(name);
        return this;
    }
    public DummyZutat einheit(Einheit einheit){
        nahrungsmittel.setEinheit(einheit);
        return this;
    }
    
    public DummyZutat verfuegbareMengeAmMarkt(int menge){
        nahrungsmittel.setVerfuegbareGesamtMenge(menge);
        return this;
    }
    
    public DummyZutat menge(double menge){
        zutat.setMenge(menge);
        return this;
    }
    
    public DummyZutat kategorie(SpeisenUndNahrungsmittelKategorie kategorie){
        nahrungsmittel.setKategorie(kategorie);
        return this;
    }
    
    public Zutat erstelle(){
        zutat.setNahrungsmittel(nahrungsmittel);
        return zutat;
    }
    
    public static Zutat kartoffeln(){
        return new DummyZutat().name("Kartoffeln").einheit(Einheit.GRAMM).menge(1000).kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).erstelle();
    }
    
    public static Zutat steaks(){
        return new DummyZutat().name("Steaks").einheit(Einheit.GRAMM).menge(500).kategorie(SpeisenUndNahrungsmittelKategorie.FLEISCH).erstelle();
    }
    
    public static Zutat garnelen(){
        return new DummyZutat().name("Garnelen").einheit(Einheit.GRAMM).menge(200).kategorie(SpeisenUndNahrungsmittelKategorie.FISCH).erstelle();
    }
}
