/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.entities.Speise;
import de.vawi.kuechenchefApp.speiseplan.Kantine;
import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import de.vawi.kuechenchefApp.speiseplan.Tag;

/**
 *
 * @author Tatsch
 */
public class DummySpeiseplan {
    
    private Speiseplan plan = new Speiseplan();
    private int tage = 0;
    public DummySpeiseplan fuerKantine(Kantine kantine){
        plan.setKantine(kantine);
        return this;
    }
    
    public DummySpeiseplan plusTag(Speise erste, Speise zweite, Speise dritte){
        Tag t = new Tag(tage++);
        t.setBeliebtesteSpeise(erste);
        t.setZweitbeliebtesteSpeise(zweite);
        t.setDrittbeliebtesteSpeise(dritte);
        plan.fuegeTagHinzu(t);
        return this;
    }
    
    public Speiseplan erstelle(){
        return plan;
    }
    
    
    
    
}
