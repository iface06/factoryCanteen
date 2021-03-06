

package de.vawi.factoryCanteen.persistence.files;

import de.vawi.factoryCanteen.persistence.files.CsvLineSeperator;
import java.util.Collection;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class CsvLineSeperatorTest {

    @Test
    public void testLineSeparation() {
        String zeile = "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11";
        CsvLineSeperator seperator = new CsvLineSeperator();
        List<String> cells = seperator.separiere(zeile);
        
        assertEquals(6, cells.size());
        assertEquals("1000", cells.get(0));
        assertEquals("g", cells.get(1));
        assertEquals("Buttergemuese TK", cells.get(2));
        assertEquals("", cells.get(3));
        assertEquals("5,42", cells.get(4));
        assertEquals("11", cells.get(5));
    }
}