

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.lieferanten.NahrungsmittelErsteller;
import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.entities.Einheit;
import de.vawi.kuechenchefApp.entities.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class NahrungsmittelErstellerTest {
    List<String> preisListenZeile = new CsvZeileSeparator().separiere("1000,\"g\",\"Kartoffeln\",,\"2,64\",1000");
    NahrungsmittelErsteller ersteller = new NahrungsmittelErsteller();

    @Test
    public void testName() {
        Nahrungsmittel nahrungsmittel = ersteller.erstelle(preisListenZeile);
        
        assertEquals("Kartoffeln", nahrungsmittel.getName());
    }
    
    @Test
    public void testEinheit() {
        Nahrungsmittel nahrungsmittel = ersteller.erstelle(preisListenZeile);
        
        assertEquals(Einheit.GRAMM, nahrungsmittel.getEinheit());
    }
    
    @Test
    public void testKategorie(){
        Nahrungsmittel nahrungsmittel = ersteller.erstelle(preisListenZeile);
        
        assertEquals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, nahrungsmittel.getKategorie());
    }

}