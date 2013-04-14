
package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.speisen.Speise;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tatsch
 */
public class SpeiseplanDruckerTest {
    
    @Test
    public void testUeberschrift() {
        List<Tag> tage = new ArrayList<Tag>();
        
        for(int periode = 1; periode <= 3; periode++){
            for(int tag = 1; tag <= 5; tag++){
                tage.add(createTag(periode, tag));
            }
        }
        
        Menu plan = new Menu(Kantine.ESSEN, tage);
        
        String ausdruck = new SpeiseplanDrucker().drucke(plan);
        
        System.out.print(ausdruck);
        assertTrue(ausdruck.startsWith("Speiseplan\n\n"));
    }

    private Tag createTag(int periode, int tags) {
        Tag tag = new Tag(tags);
        Speise speise1 = createSpeise(periode, tags, "Beliebteste Speise");
        tag.setBeliebtesteSpeise(speise1);
        Speise speise2 = createSpeise(periode, tags, "Zweitbeliebteste Speise");
        tag.setZweitbeliebtesteSpeise(speise2);
        Speise speise3 = createSpeise(periode, tags, "Drittbeliebteste Speise");
        tag.setDrittbeliebtesteSpeise(speise3);
        return tag;
    }

    private Speise createSpeise(int periode, int tag, String name) {
        Speise speise1 = new Speise();
        speise1.setName(name + " " + periode + " " + tag);
        return speise1;
    }
}
