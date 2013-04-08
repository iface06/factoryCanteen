

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.lieferanten.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.experimental.categories.*;


public class ImportIntegrationTest {   
    
    @Test
    public void testImportSpeisenVonDateiHitlisteUndRezepte() {
        importierePreislisten();
        importiereSpeisen();
        assertEquals(108, SpeisenVerwaltung.getInstanz().size());
    }
    
    @Test
    public void testAlleSpeisenHabenEinenNamen(){
        assertTrue(SpeisenVerwaltung.getInstanz().size() > 0);
        for (Speise speise : SpeisenVerwaltung.getInstanz()) {
            assertFalse(speise.getName().isEmpty());
        }
    }
    
    @Test
    public void testKategorienEntsprechendErsteZutat(){
        assertTrue(SpeisenVerwaltung.getInstanz().size() > 0);
        for (Speise speise : SpeisenVerwaltung.getInstanz()) {
            assertEquals(speise.getZutaten().get(0).getKategorie(), speise.getKategorie());
        }
    }

    private void importierePreislisten() {
        PreisListenImport importer = new PreisListenImport("importDateien");
        importer.importFiles();
    }

    private void importiereSpeisen() {
        SpeisenImport importer = new SpeisenImport("importDateien");
        importer.importFiles();
    }

}