/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.dummies;

import de.vawi.kuechenchefApp.entities.Ingredient;
import de.vawi.kuechenchefApp.entities.Unit;
import de.vawi.kuechenchefApp.entities.Food;
import de.vawi.kuechenchefApp.entities.FoodCategory;

/**
 *
 * @author Tatsch
 */
public class DummyZutat {

    private Ingredient zutat = new Ingredient();
    private Food nahrungsmittel = new Food();
    
    public DummyZutat name(String name){
        nahrungsmittel.setName(name);
        return this;
    }
    public DummyZutat einheit(Unit einheit){
        nahrungsmittel.setEinheit(einheit);
        return this;
    }
    
    public DummyZutat verfuegbareMengeAmMarkt(int menge){
        nahrungsmittel.setVerfuegbareGesamtMenge(menge);
        return this;
    }
    
    public DummyZutat menge(double menge){
        zutat.setAmount(menge);
        return this;
    }
    
    public DummyZutat kategorie(FoodCategory kategorie){
        nahrungsmittel.setKategorie(kategorie);
        return this;
    }
    
    public Ingredient erstelle(){
        zutat.setFood(nahrungsmittel);
        return zutat;
    }
    
    public static Ingredient kartoffeln(){
        return new DummyZutat().name("Kartoffeln").einheit(Unit.GRAMM).menge(1000).kategorie(FoodCategory.VEGETARIAN).erstelle();
    }
    
    public static Ingredient steaks(){
        return new DummyZutat().name("Steaks").einheit(Unit.GRAMM).menge(500).kategorie(FoodCategory.MEAT).erstelle();
    }
    
    public static Ingredient garnelen(){
        return new DummyZutat().name("Garnelen").einheit(Unit.GRAMM).menge(200).kategorie(FoodCategory.FISH).erstelle();
    }
}
