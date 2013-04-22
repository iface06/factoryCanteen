package de.vawi.kuechenchefApp.entities;

import de.vawi.kuechenchefApp.entities.Dish;
import java.io.Serializable;

/**
 * Diese Klasse repraensntiert einen Tag innerhalb eines Speiseplans
 * @author Max
 */
public class Day implements Serializable {

    private int nummer;
    private Dish beliebtesteSpeise;
    private Dish zweitbeliebtesteSpeise;
    private Dish drittbeliebtesteSpeise;
    
    /**
     * Konstrutkor 
     * @param nummer Die Tage innerhalb eines Plans werden druch nummeriert, diese Nummer wird dem Konstrukor mitgegeben. 
     */
    public Day(int nummer) {
        this.nummer = nummer;
    }
    
    /**
     * Setzt die beliebtestes Speise, die an diesem Tag angeboten wird.
     * @param beliebtesteSpeise die Speise mit der hoechsten Beliebtheit an diesem Tag
     */
    public void setBeliebtesteSpeise(Dish beliebtesteSpeise) {
        this.beliebtesteSpeise = beliebtesteSpeise;
    }
    
    /**
     * Setzt die zweit beliebtestes Speise, die an diesem Tag angeboten wird.
     * @param zweitbeliebtesteSpeise die Speise mit der zweit hoechsten Beliebtheit an diesem Tag
     */
    public void setZweitbeliebtesteSpeise(Dish zweitbeliebtesteSpeise) {
        this.zweitbeliebtesteSpeise = zweitbeliebtesteSpeise;
    }
    
    /**
     * Setzt die dritt beliebtestes Speise, die an diesem Tag angeboten wird.
     * @param drittbeliebtesteSpeise die Speise mit der dritt hoechsten Beliebtheit an diesem Tag
     */
    public void setDrittbeliebtesteSpeise(Dish drittbeliebtesteSpeise) {
        this.drittbeliebtesteSpeise = drittbeliebtesteSpeise;
    }
     /**
      * 
      * @return gibt die Nummer des Tages zurueck
      */
    public int getNummer() {
        return nummer;
    }
    
    /**
     * 
     * @return gibt die beliebteste Speise dieses Tages zurueck
     */
    public Dish getBeliebtesteSpeise() {
        return beliebtesteSpeise;
    }
    
    /**
     * 
     * @return gibt die zweitbeliebteste Speise dieses Tages zurueck
     */
    public Dish getZweitbeliebtesteSpeise() {
        return zweitbeliebtesteSpeise;
    }
    
    /**
     * 
     * @return gibt die drittbeliebteste Speise dieses Tages zurueck
     */
    public Dish getDrittbeliebtesteSpeise() {
        return drittbeliebtesteSpeise;
    }


}
