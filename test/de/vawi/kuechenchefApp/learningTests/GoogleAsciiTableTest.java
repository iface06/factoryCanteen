/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.learningTests;

import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.ASCIITableHeader;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tatsch
 */
public class GoogleAsciiTableTest {
    
     @Test
     public void hello() {
         
         ASCIITableHeader[] header = new ASCIITableHeader[]{new ASCIITableHeader("Tag 1", ASCIITable.ALIGN_LEFT), 
                                                            new ASCIITableHeader("Tag 2", ASCIITable.ALIGN_LEFT),
                                                            new ASCIITableHeader("Tag 3", ASCIITable.ALIGN_LEFT), 
                                                            new ASCIITableHeader("Tag 4", ASCIITable.ALIGN_LEFT),
                                                            new ASCIITableHeader("Tag 5", ASCIITable.ALIGN_LEFT)};
         
         List<String> firstRow = new ArrayList<String>();
         firstRow.add("Beliebtestes Essen 1");
         firstRow.add("Beliebtestes Essen 2");
         firstRow.add("Beliebtestes Essen 3");
         firstRow.add("Beliebtestes Essen 4");
         firstRow.add("Beliebtestes Essen 5");
         
         List<String> secondRow = new ArrayList<String>();
         secondRow.add("Zweitbeliebteste Essen 1");
         secondRow.add("Zweitbeliebteste Essen 2");
         secondRow.add("Zweitbeliebteste Essen 3");
         secondRow.add("Zweitbeliebteste Essen 4");
         secondRow.add("Zweitbeliebteste Essen 5");
         
         List<String> thirdRow = new ArrayList<String>();
         thirdRow.add("Drittbeliebteste Essen 1");
         thirdRow.add("Drittbeliebteste Essen 2");
         thirdRow.add("Drittbeliebteste Essen 3");
         thirdRow.add("Drittbeliebteste Essen 4");
         thirdRow.add("Drittbeliebteste Essen 5");
         
         String table = ASCIITable.getInstance().getTable(header, new String[][]{firstRow.toArray(new String[]{}), secondRow.toArray(new String[]{}), thirdRow.toArray(new String[]{})});
         System.out.print(table);
         assertFalse(table.isEmpty());
     }
}
