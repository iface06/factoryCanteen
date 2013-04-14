package de.vawi.kuechenchefApp.dateien;


import java.io.IOException;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class DateiLeserTest {
    
    String zeilen;
    String log = new String();
    
    @Before
    public void erstelleTestDateiKontent(){
        zeilen = new String();
        zeilen += "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11\r\n";
        zeilen += "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11";
    }
    
    @Test
    public void testeDateiEinlesen() {
        DateiLeser leser = new TestableDateiLeser("TestDatei");
        Datei datei = leser.leseDatei();
    
        assertTrue(datei.iterator().hasNext());
    }
    
    class TestableDateiLeser extends DateiLeser{

        public TestableDateiLeser(String dateiName) {
            super(dateiName);
        }
        
        

        @Override
        protected VawiDateiManager erstelleDateiManager() {
            TestableDateiManager manager = new TestableDateiManager(dateiName);
            manager.setDateiInhalt(zeilen);
            return manager;
        }

        @Override
        protected void behandelFehlerfall(IOException ex) {
            log = ex.getMessage();
        }
        
    }
    
    enum TestableDatei implements Datei{
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
