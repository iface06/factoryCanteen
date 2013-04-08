
package de.vawi.kuechenchefApp.kostenaufstellung;

import de.vawi.kuechenchefApp.einkaufsliste.*;
import de.vawi.kuechenchefApp.lieferanten.*;
import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class KostenUebersichtDruckerTest {

    @Test
    public void testKostenuebersichtAusdruck() {
        KostenUebersicht kosten = new KostenUebersicht();
        kosten.setGesamtKosten(35000.0);
        kosten.setLieferKostenGesamt(5000.0);
        Kostenaufstellung kostenaufstellung = new Kostenaufstellung();
        Lieferant lieferant = new Bauer();
        lieferant.setName("Bauer Huber");
        kostenaufstellung.setLieferant(lieferant);
        EinkaufslistenPosition position = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Kartoffeln").einheit(Einheit.GRAMM).menge(1000.0).preis(50.0).erstelle();
        EinkaufslistenPosition position1 = new DummyEinkaufslistenPosition().fuerNahrungsmittel("Rinderfilet").einheit(Einheit.GRAMM).menge(1000.0).preis(150.0).erstelle();
        kostenaufstellung.setEinkaufslistenPositionsListe(Arrays.asList(position, position1));
        kosten.setKostenaufstellungenProLieferant(Arrays.asList(kostenaufstellung));
        KostenUebersichtDrucker drucker = new KostenUebersichtDrucker();
        String uebersicht = drucker.drucke(kosten);
        
        System.out.print(uebersicht);
        
        
    }
    
}