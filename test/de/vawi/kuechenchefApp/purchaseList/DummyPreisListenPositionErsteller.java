

package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.entities.PreisListenPosition;
import de.vawi.kuechenchefApp.entities.Distributer;
import de.vawi.kuechenchefApp.entities.Supplier;
import de.vawi.kuechenchefApp.entities.Farmer;
import de.vawi.kuechenchefApp.entities.Food;
import de.vawi.kuechenchefApp.entities.Unit;
import org.junit.Ignore;

@Ignore
public class DummyPreisListenPositionErsteller {
    
    public static final int GROSSHAENDLER = 1;
    public static final int BAUER = 2;
    
    private PreisListenPosition position = new PreisListenPosition();

    public DummyPreisListenPositionErsteller() {
        position.setNahrungsmittel(new Food());
        
    }
    
    public DummyPreisListenPositionErsteller nahrungsmittel(String name, Unit einheit){
        position.getNahrungsmittel().setName(name);
        position.getNahrungsmittel().setEinheit(einheit);
        return this;
    }
    
    public DummyPreisListenPositionErsteller bauer(String name, double entfernung){
        Supplier lieferant = new Farmer();
        lieferant.setName(name);
        lieferant.setLieferKostenFaktor(entfernung);
        position.setLieferant(lieferant);
        return this;
    }
    
    public DummyPreisListenPositionErsteller grosshaendler(String name, double lieferkostenSatz){
        Supplier lieferant = new Distributer();
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
