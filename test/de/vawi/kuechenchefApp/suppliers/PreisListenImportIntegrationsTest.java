
package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.DateiLeser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * Wird wohl ne Art Integrationstest... 
 * @author Tatsch
 */


public class PreisListenImportIntegrationsTest {    
    
    @Test
    public void testLeseAlleDateienEin() {
        PreisListenImport importer = new PreisListenImport("importDateien");
        importer.importFiles();
        assertEquals(467, LieferantenVerwaltung.getInstanz().anzahlPreisListenPositionen());
    }
}
