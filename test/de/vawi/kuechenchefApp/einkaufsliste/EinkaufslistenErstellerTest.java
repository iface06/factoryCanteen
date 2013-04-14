package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.lieferanten.*;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import de.vawi.kuechenchefApp.speisen.*;
import de.vawi.kuechenchefApp.speiseplan.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author Matthias
 */
public class EinkaufslistenErstellerTest {

    private static LieferantenVerwaltung verwaltung;

    @BeforeClass
    public static void initLieferantenVerwaltung() {
        PreisListenPosition kartoffelAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).bauer("Müller", 10.0).angebot(5.0, 1000.0, 10000).erstelle();
        PreisListenPosition EierA = new DummyPreisListenPositionErsteller().nahrungsmittel("Eier", Einheit.STUECK).bauer("Müller", 10.0).angebot(2.5, 30.0, 10000).erstelle();
        PreisListenPosition kartoffelAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).bauer("Meier", 5.0).angebot(7.0, 1000.0, 50000).erstelle();
        PreisListenPosition moehrenAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.GRAMM).bauer("Müller", 10.0).angebot(2.0, 500.0, 1).erstelle();
        PreisListenPosition moehrenAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.GRAMM).bauer("Meier", 5.0).angebot(1.0, 500.0, 3).erstelle();
        PreisListenPosition moehrenAngebotC = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.GRAMM).grosshaendler("GRANDLER", 1.1).angebot(1.5, 1000.0, 1).erstelle();
        verwaltung = LieferantenVerwaltung.getInstanz();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(kartoffelAngebotA, kartoffelAngebotB, moehrenAngebotA, moehrenAngebotB, moehrenAngebotC, EierA));
    }
    private EinkaufslistenErsteller ersteller;
    
    
    public void initSpeiseplaene() {
        Speiseplan plan = erzeugeDummySpeiseplan(Kantine.MUELHEIM_AN_DER_RUHR);
        Speiseplan plan1 = erzeugeDummySpeiseplan(Kantine.ESSEN);
        ersteller = new EinkaufslistenErsteller();
        ersteller.add(plan);
        ersteller.add(plan1);
    }

    @Test
    public void testErzeugeEinkauflistenPosition() {
        initSpeiseplaene();
        Einkaufsliste liste = ersteller.erzeuge();
        EinkaufslistenPosition position = liste.getPositionen().get(0);

        assertEquals(1, liste.getPositionen().size());
        assertEquals("Kartoffeln", position.getName());
        assertEquals(Einheit.STUECK, position.getEinheit());
    }

    @Test
    public void testFuegeGleichesNahrungsmittelHinzu() {
        initSpeiseplaene();
        Einkaufsliste liste = ersteller.erzeuge();
        EinkaufslistenPosition position = liste.getPositionen().get(0);

        assertEquals(1, liste.getPositionen().size());
        assertEquals(3000.0, position.getMenge(), 0.0001);
    }

    /*
     * Billigsten Lieferanten für Position findens
     * Prüfe ob angebotene Menge ausreicht
     * Passt Gebindegröße zu benötigter Menge
     * Wenn angebotene Menge nicht ausreicht nächstgünstigesten Lieferanten wählen
     * 
     */
    @Test
    public void testFindePreiswertestenLieferantenFuerEinkaufslistenposition() {
        initSpeiseplaene();
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals("Müller", liste.getPositionen().get(0).getLieferant().getName());
        
    }
    

    @Test
    public void testFindeGünstigstenGesamtPreisFuerEinkaufslistenposition() {
        initSpeiseplaene();
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(15.0, liste.getPositionen().get(0).getPreis(), 0.001);
    }
    
    @Test
    public void testGesamtmengeGroeßerAlsVoratsmengeEinesLieferanten() {
        Speise speise = erzeugeButtermöhren();
        Speiseplan planEssen = new DummySpeiseplan().fuerKantine(Kantine.ESSEN).plusTag(speise, speise, speise).erstelle();
        Speiseplan planMuehlheim = new DummySpeiseplan().fuerKantine(Kantine.MUELHEIM_AN_DER_RUHR).plusTag(speise, speise, speise).erstelle();
        ersteller = new EinkaufslistenErsteller();
        ersteller.add(planEssen);
        ersteller.add(planMuehlheim);
    
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(2, liste.getPositionen().size());
        assertEquals(1000.0, liste.getPositionen().get(0).getMenge(), 0.001);
        assertEquals("GRANDLER", liste.getPositionen().get(0).getLieferant().getName());
        assertEquals(1500.0, liste.getPositionen().get(1).getMenge(), 0.001);
        assertEquals("Meier", liste.getPositionen().get(1).getLieferant().getName());
    }
    
    @Test
    public void testOptimierungDerLieferkostenMitBauerNurEinePosition(){
        Speise buttermöhren = erzeugeButtermöhren();
        Speise bratkartoffeln = erzeugeBratkartoffeln();
        Speiseplan planEssen = new DummySpeiseplan().fuerKantine(Kantine.ESSEN).plusTag(buttermöhren, buttermöhren, buttermöhren).plusTag(bratkartoffeln, bratkartoffeln, bratkartoffeln).erstelle();
        Speiseplan planMuehlheim = new DummySpeiseplan().fuerKantine(Kantine.MUELHEIM_AN_DER_RUHR).plusTag(buttermöhren, buttermöhren, buttermöhren).plusTag(bratkartoffeln, bratkartoffeln, bratkartoffeln).erstelle();
        ersteller = new EinkaufslistenErsteller();
        ersteller.add(planEssen);
        ersteller.add(planMuehlheim);
        
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(3, liste.getPositionen().size());
        assertEquals("Meier", liste.getPositionen().get(0).getLieferant().getName());
        assertEquals(21.0, liste.getPositionen().get(0).getPreis(), 0.001);
    }
    
    @Test
    public void testOptimierungDerLieferkostenBauerMehralsEinePosition(){
        Speise buttermöhren = erzeugeButtermöhren();
        Speise bratkartoffeln = erzeugeBratkartoffeln();
        Speise eiersalat = erzeugeEiersalat();
        Speiseplan planEssen = new DummySpeiseplan().fuerKantine(Kantine.ESSEN).plusTag(buttermöhren, buttermöhren, eiersalat).plusTag(bratkartoffeln, bratkartoffeln, bratkartoffeln).erstelle();
        Speiseplan planMuehlheim = new DummySpeiseplan().fuerKantine(Kantine.MUELHEIM_AN_DER_RUHR).plusTag(buttermöhren, buttermöhren, buttermöhren).plusTag(bratkartoffeln, bratkartoffeln, bratkartoffeln).erstelle();
        ersteller = new EinkaufslistenErsteller();
        ersteller.add(planEssen);
        ersteller.add(planMuehlheim);
        
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(4, liste.getPositionen().size());
        assertEquals("Müller", liste.getPositionen().get(0).getLieferant().getName());
        assertEquals(15.0, liste.getPositionen().get(0).getPreis(), 0.001);
    }
    
    private Speiseplan erzeugeDummySpeiseplan(Kantine kantine) {
        List<Tag> tage = new ArrayList<Tag>();
        Tag tag = erzeugeTag();
        tage.add(tag);
        Speiseplan plan = new Speiseplan(kantine, tage);

        return plan;
    }

    private Tag erzeugeTag() {
        Tag tag = new Tag(1);
        Speise speise = erzeugeBratkartoffeln();
        Speise speise1 = erzeugeBratkartoffeln();
        Speise speise2 = erzeugeBratkartoffeln();
        tag.setBeliebtesteSpeise(speise);
        tag.setZweitbeliebtesteSpeise(speise1);
        tag.setDrittbeliebtesteSpeise(speise2);
        return tag;
    }

    private Speise erzeugeBratkartoffeln() {
        Zutat kartoffeln = new DummyZutat().name("Kartoffeln").einheit(Einheit.STUECK).menge(2.0).kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).erstelle();
        return new DummySpeise().name("Bratkaroffeln").mitZutat(kartoffeln).beliebtheit(1).erstelle();
    }

    protected Speise erzeugeButtermöhren() {
        Zutat moehren = new DummyZutat().name("Möhren").einheit(Einheit.STUECK).menge(2.0).kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).erstelle();
        Speise speise = new DummySpeise().name("Buttermöhren").mitZutat(moehren).erstelle();
        return speise;
    }
    
    protected Speise erzeugeEiersalat() {
        Zutat moehren = new DummyZutat().name("Eier").einheit(Einheit.STUECK).menge(4.0).kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).erstelle();
        Speise speise = new DummySpeise().name("Eiersalat").mitZutat(moehren).erstelle();
        return speise;
    }
}
