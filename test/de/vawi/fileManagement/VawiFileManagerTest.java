/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.fileManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class VawiFileManagerTest {

    @Test
    public void testReadFile() throws IOException {
        System.out.println("eof");
        de.vawi.fileManagement.VawiFileManager datei = new TestableDateiManager("testFile");
        datei.openInFile();


        List<String> lines = new ArrayList<>();
        while (!datei.eof()) {
            String line = datei.readLine();
            lines.add(line);
        }

        datei.closeInFile();

        assertEquals(2, lines.size());
        assertEquals("1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11", lines.get(0));
        assertNull(lines.get(1));
    }

    class TestableDateiManager extends de.vawi.fileManagement.VawiFileManager {

        public TestableDateiManager(String in_name) {
            super(in_name);
        }

        @Override
        protected BufferedReader createBufferedReader() throws FileNotFoundException {
            return new BufferedReader(new StringReader("1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11"));
        }
    }
}
