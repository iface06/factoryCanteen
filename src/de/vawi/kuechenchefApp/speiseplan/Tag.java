package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.entities.Speise;

/**
 * Diese Klasse repraensntiert einen Tag innerhalb eines Speiseplans
 * @author Max
 */
public class Tag{

    private int nummer;
    private Speise beliebtesteSpeise;
    private Speise zweitbeliebtesteSpeise;
    private Speise drittbeliebtesteSpeise;
    
    /**
     * Konstrutkor 
     * @param nummer Die Tage innerhalb eines Plans werden druch nummeriert, diese Nummer wird dem Konstrukor mitgegeben. 
     */
    public Tag(int nummer) {
        this.nummer = nummer;
    }
    
    /**
     * Setzt die beliebtestes Speise, die an diesem Tag angeboten wird.
     * @param beliebtesteSpeise die Speise mit der hoechsten Beliebtheit an diesem Tag
     */
    public void setBeliebtesteSpeise(Speise beliebtesteSpeise) {
        this.beliebtesteSpeise = beliebtesteSpeise;
    }
    
    /**
     * Setzt die zweit beliebtestes Speise, die an diesem Tag angeboten wird.
     * @param zweitbeliebtesteSpeise die Speise mit der zweit hoechsten Beliebtheit an diesem Tag
     */
    public void setZweitbeliebtesteSpeise(Speise zweitbeliebtesteSpeise) {
        this.zweitbeliebtesteSpeise = zweitbeliebtesteSpeise;
    }
    
    /**
     * Setzt die dritt beliebtestes Speise, die an diesem Tag angeboten wird.
     * @param drittbeliebtesteSpeise die Speise mit der dritt hoechsten Beliebtheit an diesem Tag
     */
    public void setDrittbeliebtesteSpeise(Speise drittbeliebtesteSpeise) {
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
    public Speise getBeliebtesteSpeise() {
        return beliebtesteSpeise;
    }
    
    /**
     * 
     * @return gibt die zweitbeliebteste Speise dieses Tages zurueck
     */
    public Speise getZweitbeliebtesteSpeise() {
        return zweitbeliebtesteSpeise;
    }
    
    /**
     * 
     * @return gibt die drittbeliebteste Speise dieses Tages zurueck
     */
    public Speise getDrittbeliebtesteSpeise() {
        return drittbeliebtesteSpeise;
    }


}
