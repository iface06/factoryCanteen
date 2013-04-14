
package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.purchaseList.PurchaseListPosition;
import de.vawi.kuechenchefApp.purchaseList.PurchaseList;
import de.vawi.kuechenchefApp.entities.Supplier;
import de.vawi.kuechenchefApp.entities.Food;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class EinkaufslisteTest {
    PurchaseList liste = new PurchaseList();

    @Test
    public void testFindeVorhandenePositionDurchNahrungsmittel() {
        Food kartoffeln = new Food();
        liste.hinzufügenEinkaufslistenPosition(new PurchaseListPosition(kartoffeln));
        kartoffeln.setName("Kartoffeln");
        PurchaseListPosition position = liste.findePositionDurchNahrungsmittel(kartoffeln);
    
        assertEquals(kartoffeln.getName(), position.getName());
    }
    
    @Test
    public void testFindeNichtVorhandenePositionDurchNahrungsmittel() {
        Food kartoffeln = new Food();
        kartoffeln.setName("Kartoffeln");
        PurchaseListPosition position = liste.findePositionDurchNahrungsmittel(kartoffeln);
    
        assertEquals(kartoffeln.getName(), position.getName());
    }
    
    @Test
    public void testHoleLieferanten(){
        PurchaseListPosition positionA = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Kartoffeln").vomBauer("Huber", 10).erstelle();
        PurchaseListPosition positionB = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Kartoffeln").vomBauer("Meier", 10).erstelle();
        liste.hinzufügenEinkaufslistenPosition(positionB);
        liste.hinzufügenEinkaufslistenPosition(positionA);
        
        Set<Supplier> lieferanten = liste.holeLieferanten();
        
        assertEquals(2, lieferanten.size());
        
    }
}