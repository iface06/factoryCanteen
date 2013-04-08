/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.speisen;

/**
 *
 * @author Tatsch
 */
public class DummySpeise {
    
    private Speise speise = new Speise();
    
    
    public DummySpeise beliebtheit(int beliebtheit){
        speise.setBeliebtheit(beliebtheit);
        return this;
    }
    public DummySpeise name(String name){
        speise.setName(name);
        return this;
    }
    
    public DummySpeise mitZutat(Zutat zutat){
        speise.addZutat(zutat);
        return this;
    }

    public Speise erstelle() {
        return speise;
    }
    
    
}
