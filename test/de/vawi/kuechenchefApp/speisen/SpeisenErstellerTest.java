

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.entities.Speise;
import org.junit.*;
import static org.junit.Assert.*;


public class SpeisenErstellerTest {
    
    @Test
    public void testSpeisenErstellungAusHitlisteZeile() {
        String hitlisteZeile = "1,\"Bohneneintopf Mexiko\"";
        Speise speise = new SpeisenErsteller().erstelle(hitlisteZeile);
        
        assertEquals(1, speise.getBeliebtheit());
        assertEquals("Bohneneintopf Mexiko", speise.getName());
    }
    

}