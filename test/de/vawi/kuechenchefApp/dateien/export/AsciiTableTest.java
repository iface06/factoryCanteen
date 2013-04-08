
package de.vawi.kuechenchefApp.dateien.export;

import de.vawi.kuechenchefApp.export.Cell;
import de.vawi.kuechenchefApp.export.AsciiTable;
import de.vawi.kuechenchefApp.export.Row;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tatsch
 */
public class AsciiTableTest {
    
    @Test
    public void testAddRows() {
        AsciiTable table = new AsciiTable();
        Row row = table.row();
        assertNotNull(row);
    }
    @Test
    public void testAddCell() {
        AsciiTable table = new AsciiTable();
        Row row = table.row().cell("TEstCell");
        assertNotNull(row);
    }
    
    @Test
    public void testWritingTableWithOne(){
        AsciiTable table = new AsciiTable();
        table.row().cell("Nachrichten");
        table.row().cell("Hello World!");
        table.row().cell("Hallo Welt!");
        
        String tables = table.writeTable();
        System.out.print(tables);
        assertFalse(tables.isEmpty());
    }
    @Test
    public void testWritingTableWithToweColumns(){
        AsciiTable table = new AsciiTable();
        table.row().cell("Nachrichten").cell("Antwort");
        table.row().cell("Hello World!").cell("Have a nice day!");
        table.row().cell("Hallo Welt!").cell("Einen schönen Tag!");
        
        String tables = table.writeTable();
        System.out.print(tables);
        assertFalse(tables.isEmpty());
    }
}
