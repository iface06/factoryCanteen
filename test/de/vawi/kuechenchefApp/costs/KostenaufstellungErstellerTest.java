package de.vawi.kuechenchefApp.costs;

import de.vawi.kuechenchefApp.purchaseList.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author Sonja
 */
public class KostenaufstellungErstellerTest {

    private PurchaseList einkaufsliste;
    
    @Before
    public void initEinkaufsliste(){
        einkaufsliste = erstelleEinkaufsliste(DummyEinkaufslistenPosition.eier(), DummyEinkaufslistenPosition.rinderhuefte());
    }
    
    @Test
    public void testBerechneGesamtKosten() {
 
        KostenaufstellungErsteller ersteller = new KostenaufstellungErsteller();
        ersteller.setEinkaufsliste(einkaufsliste);
        KostenUebersicht ubersicht = ersteller.erstelle();
        
        assertEquals(850.0, ubersicht.getGesamtKosten(), 0.001);
    }

    @Test
    public void testGruppierungNachLieferanten() {    
        KostenaufstellungErsteller ersteller = new KostenaufstellungErsteller();
        ersteller.setEinkaufsliste(einkaufsliste);
        KostenUebersicht ubersicht = ersteller.erstelle();
        
        assertEquals(2, ubersicht.getKostenaufstellungenProLieferant().size());
        assertEquals("Heinrich", ubersicht.getKostenaufstellungenProLieferant().get(0).getLieferant().getName());
        assertEquals("Otto Gourmet", ubersicht.getKostenaufstellungenProLieferant().get(1).getLieferant().getName());
    }
    
    @Test
    public void testBerechneEinkaufsKostenProLieferant() {
        KostenaufstellungErsteller ersteller = new KostenaufstellungErsteller();
        ersteller.setEinkaufsliste(einkaufsliste);
        KostenUebersicht ubersicht = ersteller.erstelle();
        
        assertEquals(2, ubersicht.getKostenaufstellungenProLieferant().size());
        assertEquals(500.0, ubersicht.getKostenaufstellungenProLieferant().get(0).berechneEinkaufsKostenProLieferant(), 0.001);
        assertEquals(300.0, ubersicht.getKostenaufstellungenProLieferant().get(1).berechneEinkaufsKostenProLieferant(), 0.001);
    }
    
    @Test
    public void testBerechneLieferKostenProLieferant() {
        KostenaufstellungErsteller ersteller = new KostenaufstellungErsteller();
        ersteller.setEinkaufsliste(einkaufsliste);
        KostenUebersicht ubersicht = ersteller.erstelle();
        
        assertEquals(2, ubersicht.getKostenaufstellungenProLieferant().size());
        assertEquals(20.0, ubersicht.getKostenaufstellungenProLieferant().get(0).berechneLieferKostenProLieferant(), 0.001);
        assertEquals(30.0, ubersicht.getKostenaufstellungenProLieferant().get(1).berechneLieferKostenProLieferant(), 0.001);
    }

    private PurchaseList erstelleEinkaufsliste(PurchaseListPosition... positionen) {
        PurchaseList liste = new PurchaseList();
        for (PurchaseListPosition position : positionen) {
            liste.hinzufügenEinkaufslistenPosition(position);            
        }
        return liste;
    }
}