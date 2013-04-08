package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class LieferantenVerwaltungTest {

    private static LieferantenVerwaltung verwaltung;
    
    @AfterClass
    public static void afterClass() {
        LieferantenVerwaltung.INSTANZ = null;
    }

    @BeforeClass
    public static void beforeClass() {
        PreisListenPosition kartoffelAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).grosshaendler("Schmidt", 5).angebot(5.0, 100.0, 1000).erstelle();
        PreisListenPosition kartoffelAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).bauer("Meier", 5).angebot(10.0, 100.0, 1000).erstelle();
        PreisListenPosition moehrenAngebot = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.GRAMM).bauer("Huber", 5).angebot(1.0, 100.0, 500).erstelle();
        verwaltung = new LieferantenVerwaltung();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(kartoffelAngebotA, kartoffelAngebotB, moehrenAngebot));
    }

    @Test
    public void testFindeKartoffelnSortiertNachPreis() {
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");
        List<PreisListenPosition> positionen = verwaltung.findeDurchNahrungsmittel(kartoffeln);

        assertEquals(2, positionen.size());
        assertEquals(5.0, positionen.get(0).getPreis(), 0.0001);
        assertEquals(10.0, positionen.get(1).getPreis(), 0.0001);
    }

    @Test
    public void testFindeMoehren() {
        Nahrungsmittel moehren = new Nahrungsmittel();
        moehren.setName("Möhren");
        List<PreisListenPosition> positionen = verwaltung.findeDurchNahrungsmittel(moehren);

        assertEquals(1, positionen.size());
    }

    @Test
    public void testFindeMoehrenVonHuber() {
        Nahrungsmittel moehren = new Nahrungsmittel();
        moehren.setName("Möhren");
        Lieferant lieferant = new Bauer();
        lieferant.setName("Huber");
        PreisListenPosition positionen = verwaltung.findeAngebotFuerNahrungsmittelVonLieferant(moehren, lieferant);

        assertNotNull(positionen);
        assertEquals(lieferant, positionen.getLieferant());
        assertEquals(moehren, positionen.getNahrungsmittel());
    }
}
