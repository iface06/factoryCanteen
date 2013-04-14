package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.speiseplan.Kantine;
import de.vawi.kuechenchefApp.speiseplan.Menu;
import de.vawi.kuechenchefApp.speiseplan.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class ZutatenKalkulatorTest {

    @Test
    public void testMengenBerechnung() {
        Menu plan = erzeugeDummySpeiseplan();
        ZutatenKalkulator kalkulator = new ZutatenKalkulator();
        Map<Nahrungsmittel, Double> mengen = kalkulator.berechneGesamtMengen(plan);

        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");

        assertEquals(1, mengen.size());
        assertEquals(1502.0, mengen.get(kartoffeln), 0.0001);
    }
    
    private Menu erzeugeDummySpeiseplan() {
        List<Tag> tage = new ArrayList<Tag>();
        Tag tag = erzeugeTag();
        tage.add(tag);
        Menu plan = new Menu(Kantine.ESSEN, tage);

        return plan;
    }

    private Tag erzeugeTag() {
        Tag tag = new Tag(1);
        Speise speise = erzeugeSpeise();
        Speise speise1 = erzeugeSpeise();
        Speise speise2 = erzeugeSpeise();
        tag.setBeliebtesteSpeise(speise);
        tag.setZweitbeliebtesteSpeise(speise1);
        tag.setDrittbeliebtesteSpeise(speise2);
        return tag;
    }

    private Speise erzeugeSpeise() {
        Speise speise = new Speise();
        Zutat zutat = erzeugeZutat();
        speise.addZutat(zutat);
        return speise;
    }

    private Zutat erzeugeZutat() {
        Zutat zutat = new Zutat();
        Nahrungsmittel nahrungsmittel = erzeugeNahrungsmittel(zutat);
        zutat.setMenge(2.0);
        zutat.setNahrungsmittel(nahrungsmittel);
        return zutat;
    }

    private Nahrungsmittel erzeugeNahrungsmittel(Zutat zutat) {
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel();
        nahrungsmittel.setName("Kartoffeln");
        nahrungsmittel.setEinheit(Einheit.STUECK);
        return nahrungsmittel;
    }
}
