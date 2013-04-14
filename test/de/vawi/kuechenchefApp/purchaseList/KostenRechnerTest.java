package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.purchaseList.KostenRechner;
import de.vawi.kuechenchefApp.purchaseList.PurchaseList;
import de.vawi.kuechenchefApp.entities.Unit;
import org.junit.*;
import static org.junit.Assert.*;

public class KostenRechnerTest {

    private PurchaseList einkaufsliste;

    @Before
    public void before() {
        einkaufsliste = new PurchaseList();
        DummyEinkaufslistenPosition kartoffeln = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Kartoffeln").menge(50.0).einheit(Unit.GRAMM).vomBauer("Huber", 10).preis(500.0);
        DummyEinkaufslistenPosition moehren = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Möhren").menge(50.0).einheit(Unit.GRAMM).vomGrosshaendler("Meier", 1.1).preis(500.0);
        einkaufsliste.hinzufügenEinkaufslistenPosition(kartoffeln.erstelle());
        einkaufsliste.hinzufügenEinkaufslistenPosition(moehren.erstelle());

    }

    @Test
    public void testBerechneGesamtkostenFuerNahrungsmittel() {

        KostenRechner rechner = new KostenRechner();
        double kosten = rechner.berechneGesamtkostenFuerNahrungsmittel(einkaufsliste);

        assertEquals(1000.0, kosten, 0.001);
    }

    @Test
    public void testBerechneGesamtkostenFuerLieferung() {

        KostenRechner rechner = new KostenRechner();
        double kosten = rechner.berechneGesamtkostenFuerLieferung(einkaufsliste);

        assertEquals(70.0, kosten, 0.001);
    }
    
    @Test
    public void testBerechneGesamtkosten() {

        KostenRechner rechner = new KostenRechner();
        double kosten = rechner.berechneGesamtkosten(einkaufsliste);

        assertEquals(1070.0, kosten, 0.001);
    }
    
    
}