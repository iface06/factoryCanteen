
package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.lieferanten.*;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class EinkaufslisteTest {
    Einkaufsliste liste = new Einkaufsliste();

    @Test
    public void testFindeVorhandenePositionDurchNahrungsmittel() {
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        liste.hinzufügenEinkaufslistenPosition(new EinkaufslistenPosition(kartoffeln));
        kartoffeln.setName("Kartoffeln");
        EinkaufslistenPosition position = liste.findePositionDurchNahrungsmittel(kartoffeln);
    
        assertEquals(kartoffeln.getName(), position.getName());
    }
    
    @Test
    public void testFindeNichtVorhandenePositionDurchNahrungsmittel() {
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");
        EinkaufslistenPosition position = liste.findePositionDurchNahrungsmittel(kartoffeln);
    
        assertEquals(kartoffeln.getName(), position.getName());
    }
    
    @Test
    public void testHoleLieferanten(){
        EinkaufslistenPosition positionA = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Kartoffeln").vomBauer("Huber", 10).erstelle();
        EinkaufslistenPosition positionB = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Kartoffeln").vomBauer("Meier", 10).erstelle();
        liste.hinzufügenEinkaufslistenPosition(positionB);
        liste.hinzufügenEinkaufslistenPosition(positionA);
        
        Set<Lieferant> lieferanten = liste.holeLieferanten();
        
        assertEquals(2, lieferanten.size());
        
    }
}