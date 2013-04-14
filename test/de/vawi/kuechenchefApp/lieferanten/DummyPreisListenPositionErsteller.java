

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.entities.Grosshaendler;
import de.vawi.kuechenchefApp.entities.Lieferant;
import de.vawi.kuechenchefApp.entities.Bauer;
import de.vawi.kuechenchefApp.entities.Nahrungsmittel;
import de.vawi.kuechenchefApp.entities.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import org.junit.Ignore;

@Ignore
public class DummyPreisListenPositionErsteller {
    
    public static final int GROSSHAENDLER = 1;
    public static final int BAUER = 2;
    
    private PreisListenPosition position = new PreisListenPosition();

    public DummyPreisListenPositionErsteller() {
        position.setNahrungsmittel(new Nahrungsmittel());
        
    }
    
    public DummyPreisListenPositionErsteller nahrungsmittel(String name, Einheit einheit){
        position.getNahrungsmittel().setName(name);
        position.getNahrungsmittel().setEinheit(einheit);
        return this;
    }
    
    public DummyPreisListenPositionErsteller bauer(String name, double entfernung){
        Lieferant lieferant = new Bauer();
        lieferant.setName(name);
        lieferant.setLieferKostenFaktor(entfernung);
        position.setLieferant(lieferant);
        return this;
    }
    
    public DummyPreisListenPositionErsteller grosshaendler(String name, double lieferkostenSatz){
        Lieferant lieferant = new Grosshaendler();
        lieferant.setName(name);
        lieferant.setLieferKostenFaktor(lieferkostenSatz);
        position.setLieferant(lieferant);
        return this;
    }
    
    
    
    public DummyPreisListenPositionErsteller angebot(double preis, double gebinde, int vorat){
        position.setGebindeGroesse(gebinde);
        position.setVorratsBestand(vorat);
        position.setPreis(preis);
        return this;
    }
    
    public PreisListenPosition erstelle(){
        return position;
    }
}
