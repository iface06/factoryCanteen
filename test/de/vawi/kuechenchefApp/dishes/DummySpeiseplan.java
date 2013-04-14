/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.dishes;

import de.vawi.kuechenchefApp.entities.Dish;
import de.vawi.kuechenchefApp.menues.Canteen;
import de.vawi.kuechenchefApp.menues.Menu;
import de.vawi.kuechenchefApp.menues.Day;

/**
 *
 * @author Tatsch
 */
public class DummySpeiseplan {
    
    private Menu plan = new Menu();
    private int tage = 0;
    public DummySpeiseplan fuerKantine(Canteen kantine){
        plan.setKantine(kantine);
        return this;
    }
    
    public DummySpeiseplan plusTag(Dish erste, Dish zweite, Dish dritte){
        Day t = new Day(tage++);
        t.setBeliebtesteSpeise(erste);
        t.setZweitbeliebtesteSpeise(zweite);
        t.setDrittbeliebtesteSpeise(dritte);
        plan.fuegeTagHinzu(t);
        return this;
    }
    
    public Menu erstelle(){
        return plan;
    }
    
    
    
    
}
