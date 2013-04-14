

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.entities.Grosshaendler;
import de.vawi.kuechenchefApp.entities.Lieferant;
import de.vawi.kuechenchefApp.entities.Bauer;
import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import org.junit.*;
import static org.junit.Assert.*;


public class LieferantenErstellerTest {

    private String bauerZeile = "\"Bauer\",\"Super Hof\",1,,,";
    private String grosshaendlerZeile = "\"Grosshandel\",\"Metri AG\",1,,,";
    private CsvZeileSeparator separator = new CsvZeileSeparator();
    
    @Test
    public void testErstelleBauer() {
        
        LieferantenErsteller ersteller = new LieferantenErsteller();
        Lieferant lieferant = ersteller.erstelle(separator.separiere(bauerZeile));
        
        assertEquals(Bauer.class, lieferant.getClass());
        assertEquals("Super Hof", lieferant.getName());
        assertEquals(1.0, lieferant.getLieferKostenFaktor(), 0.001);
    }
    
    @Test
    public void testErstelleGrosshaendler() {
        
        LieferantenErsteller ersteller = new LieferantenErsteller();
        Lieferant lieferant = ersteller.erstelle(separator.separiere(grosshaendlerZeile));
        
        assertEquals(Grosshaendler.class, lieferant.getClass());
        assertEquals("Metri AG", lieferant.getName());
        assertEquals(1.0, lieferant.getLieferKostenFaktor(), 0.001);
    }

}