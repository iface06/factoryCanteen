
package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.dateien.*;
import de.vawi.kuechenchefApp.speisen.*;
import java.io.IOException;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Tatsch
 */
public class SpeiseplanExportTest {
    
    private String speisePlan;
    
    @Test
    public void testExportSpeiseplanInDatei(){
        Speise surfAndTurf = new DummySpeise().name("Surf and Turf").beliebtheit(1).mitZutat(DummyZutat.steaks()).mitZutat(DummyZutat.garnelen()).erstelle();
        DummySpeiseplan dummy = new DummySpeiseplan();
        dummy.fuerKantine(Kantine.ESSEN);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        Menu plan = dummy.erstelle();
        SpeiseplanExport exporter = new TetsableSpieseplanExport();
        exporter.export(Arrays.asList(plan));
        
        assertNotNull(speisePlan);
        assertFalse(speisePlan.isEmpty());
    }
    
    class TetsableSpieseplanExport extends SpeiseplanExport{

        List<String> inhalt = new ArrayList<>();
        @Override
        protected DateiSchreiber erstelleSchreiberFuer(Menu speiseplan) {
            return new TestableDateiSchreiber(new Datei() {

                @Override
                public String getDateiname() {
                    return "speiseplan.txt";
                }

                @Override
                public Iterator<String> iterator() {
                    return inhalt.iterator();
                }
            });
        }
    }
    
    class TestableDateiSchreiber extends DateiSchreiber{

        public TestableDateiSchreiber(Datei dateiName) {
            super(dateiName);
        }
        
        @Override
        protected DateiSchreiberManager erzeugeDatei() {
            return new DateiSchreiberManager() {

                @Override
                public void openOutFile() throws IOException {
                    
                }

                @Override
                public void writeLine(String in_str) {
                    speisePlan = in_str;
                }

                @Override
                public void closeOutFile() {
                    
                }
            };
        }
    }
    
}


