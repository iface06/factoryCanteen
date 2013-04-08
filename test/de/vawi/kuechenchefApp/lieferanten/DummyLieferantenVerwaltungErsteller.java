

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import java.util.*;
import org.junit.Ignore;


@Ignore
public class DummyLieferantenVerwaltungErsteller {
    
    private LieferantenVerwaltung verwaltung = LieferantenVerwaltung.getInstanz();
    
    public LieferantenVerwaltung erstelle(){
        return verwaltung;
    }

    public DummyLieferantenVerwaltungErsteller hinzufuegen(PreisListenPosition position){
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(position));
        return this;
    }
    
    public DummyLieferantenVerwaltungErsteller mitKartoffeln(){
        PreisListenPosition kartoffelAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).bauer("Müller", DummyPreisListenPositionErsteller.GROSSHAENDLER).angebot(5.0, 1000.0, 10000).erstelle();
        PreisListenPosition kartoffelAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).bauer("Meier", DummyPreisListenPositionErsteller.BAUER).angebot(10.0, 1000.0, 5000).erstelle();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(kartoffelAngebotA, kartoffelAngebotB));
        return this;
        
    }
    
    public DummyLieferantenVerwaltungErsteller mitMoehren(){
        PreisListenPosition moehrenAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.STUECK).bauer("Meier", DummyPreisListenPositionErsteller.BAUER).angebot(1.0, 500.0, 300).erstelle();
        PreisListenPosition moehrenAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.STUECK).bauer("Müller", DummyPreisListenPositionErsteller.GROSSHAENDLER).angebot(1.5, 1000.0, 100).erstelle();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(moehrenAngebotA));
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(moehrenAngebotB));
        return this;
        
    }
    

}
