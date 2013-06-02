package de.vawi.factoryCanteen.persistence.files;

import de.vawi.factoryCanteen.persistence.files.CsvFile;
import de.vawi.factoryCanteen.persistence.files.CsvFileReader;
import de.vawi.factoryCanteen.persistence.files.FileReader;
import de.vawi.fileManagement.VawiFileManager;
import java.io.IOException;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class CsvFileReaderTest {

    String zeilen;
    String log = new String();

    @Before
    public void erstelleTestDateiKontent() {
        zeilen = new String();
        zeilen += "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11\r\n";
        zeilen += "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11";
    }

    @Test
    public void testeDateiEinlesen() {
        CsvFileReader leser = new TestableDateiLeser();
        leser.setFileReader(createFileReader());
        CsvFile datei = leser.readFile();

        assertTrue(datei.iterator().hasNext());
    }

    private FileReader createFileReader() {
        TestableFileManagement manager = new TestableFileManagement("file.dummy");
        manager.setDateiInhalt(zeilen);
        return manager;
    }

    class TestableDateiLeser extends CsvFileReader {

        @Override
        protected void behandelFehlerfall(IOException ex) {
            log = ex.getMessage();
        }
    }

    enum TestableDatei implements CsvFile {

        TESTDATEI("testDateiOhneBedeutung");
        private String pathname;

        private TestableDatei(String pathname) {
            this.pathname = pathname;
        }

        @Override
        public String getDateiname() {
            return pathname;
        }

        @Override
        public Iterator<String> iterator() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
