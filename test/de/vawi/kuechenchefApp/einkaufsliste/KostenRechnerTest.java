package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import org.junit.*;
import static org.junit.Assert.*;

public class KostenRechnerTest {

    private Einkaufsliste einkaufsliste;

    @Before
    public void before() {
        einkaufsliste = new Einkaufsliste();
        DummyEinkaufslistenPosition kartoffeln = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Kartoffeln").menge(50.0).einheit(Einheit.GRAMM).vomBauer("Huber", 10).preis(500.0);
        DummyEinkaufslistenPosition moehren = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Möhren").menge(50.0).einheit(Einheit.GRAMM).vomGrosshaendler("Meier", 1.1).preis(500.0);
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