package de.vawi.kuechenchefApp.purchaseList;


import de.vawi.kuechenchefApp.entities.Canteen;
import de.vawi.kuechenchefApp.entities.Menu;
import de.vawi.kuechenchefApp.entities.Day;
import de.vawi.kuechenchefApp.dishes.*;
import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.entities.FoodCategory;
import java.util.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author Matthias
 */
public class PurchaseListCreatorTest {


    @Before
    public void initLieferantenVerwaltung() {
        PriceListPosition kartoffelAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Unit.GRAMM).bauer("Müller", 10.0).angebot(5.0, 1000.0, 10000).erstelle();
        PriceListPosition EierA = new DummyPreisListenPositionErsteller().nahrungsmittel("Eier", Unit.STUECK).bauer("Müller", 10.0).angebot(2.5, 30.0, 10000).erstelle();
        PriceListPosition kartoffelAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Unit.GRAMM).bauer("Meier", 5.0).angebot(7.0, 1000.0, 50000).erstelle();
        PriceListPosition moehrenAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Unit.GRAMM).bauer("Müller", 10.0).angebot(2.0, 500.0, 1).erstelle();
        PriceListPosition moehrenAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Unit.GRAMM).bauer("Meier", 5.0).angebot(1.0, 500.0, 3).erstelle();
        PriceListPosition moehrenAngebotC = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Unit.GRAMM).grosshaendler("GRANDLER", 1.1).angebot(1.5, 1000.0, 1).erstelle();
        verwaltung = new DummyCreatePurchaseListDao(Arrays.asList(kartoffelAngebotA, kartoffelAngebotB, moehrenAngebotA, moehrenAngebotB, moehrenAngebotC, EierA));
    }
    private PurchaseListCreator creator;
    private CreatePurchaseListDao verwaltung;
    
    
    public void initSpeiseplaene() {
        Menu plan = erzeugeDummySpeiseplan(Canteen.MUELHEIM_AN_DER_RUHR);
        Menu plan1 = erzeugeDummySpeiseplan(Canteen.ESSEN);
        creator = new PurchaseListCreator();
        creator.setDao(verwaltung);
        creator.add(plan);
        creator.add(plan1);
    }

    @Test
    public void testErzeugeEinkauflistenPosition() {
        initSpeiseplaene();
        PurchaseList liste = creator.erzeuge();
        PurchaseListPosition position = liste.getPositionen().get(0);

        assertEquals(1, liste.getPositionen().size());
        assertEquals("Kartoffeln", position.getName());
        assertEquals(Unit.STUECK, position.getEinheit());
    }

    @Test
    public void testFuegeGleichesNahrungsmittelHinzu() {
        initSpeiseplaene();
        PurchaseList liste = creator.erzeuge();
        PurchaseListPosition position = liste.getPositionen().get(0);

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
        PurchaseList liste = creator.erzeuge();
        assertEquals("Müller", liste.getPositionen().get(0).getLieferant().getName());
        
    }
    

    @Test
    public void testFindeGünstigstenGesamtPreisFuerEinkaufslistenposition() {
        initSpeiseplaene();
        PurchaseList liste = creator.erzeuge();
        assertEquals(15.0, liste.getPositionen().get(0).getPreis(), 0.001);
    }
    
    @Test
    public void testGesamtmengeGroeßerAlsVoratsmengeEinesLieferanten() {
        Dish speise = erzeugeButtermöhren();
        Menu planEssen = new DummySpeiseplan().fuerKantine(Canteen.ESSEN).plusTag(speise, speise, speise).erstelle();
        Menu planMuehlheim = new DummySpeiseplan().fuerKantine(Canteen.MUELHEIM_AN_DER_RUHR).plusTag(speise, speise, speise).erstelle();
        creator = new PurchaseListCreator();
        creator.setDao(verwaltung);
        creator.add(planEssen);
        creator.add(planMuehlheim);
    
        PurchaseList liste = creator.erzeuge();
        assertEquals(2, liste.getPositionen().size());
        assertEquals(1000.0, liste.getPositionen().get(0).getMenge(), 0.001);
        assertEquals("GRANDLER", liste.getPositionen().get(0).getLieferant().getName());
        assertEquals(1500.0, liste.getPositionen().get(1).getMenge(), 0.001);
        assertEquals("Meier", liste.getPositionen().get(1).getLieferant().getName());
    }
    
    @Test
    public void testOptimierungDerLieferkostenMitBauerNurEinePosition(){
        Dish buttermöhren = erzeugeButtermöhren();
        Dish bratkartoffeln = erzeugeBratkartoffeln();
        Menu planEssen = new DummySpeiseplan().fuerKantine(Canteen.ESSEN).plusTag(buttermöhren, buttermöhren, buttermöhren).plusTag(bratkartoffeln, bratkartoffeln, bratkartoffeln).erstelle();
        Menu planMuehlheim = new DummySpeiseplan().fuerKantine(Canteen.MUELHEIM_AN_DER_RUHR).plusTag(buttermöhren, buttermöhren, buttermöhren).plusTag(bratkartoffeln, bratkartoffeln, bratkartoffeln).erstelle();
        creator = new PurchaseListCreator();
        creator.setDao(verwaltung);
        creator.add(planEssen);
        creator.add(planMuehlheim);
        
        PurchaseList liste = creator.erzeuge();
        assertEquals(3, liste.getPositionen().size());
        assertEquals("Meier", liste.getPositionen().get(0).getLieferant().getName());
        assertEquals(21.0, liste.getPositionen().get(0).getPreis(), 0.001);
    }
    
    @Test
    public void testOptimierungDerLieferkostenBauerMehralsEinePosition(){
        Dish buttermöhren = erzeugeButtermöhren();
        Dish bratkartoffeln = erzeugeBratkartoffeln();
        Dish eiersalat = erzeugeEiersalat();
        Menu planEssen = new DummySpeiseplan().fuerKantine(Canteen.ESSEN).plusTag(buttermöhren, buttermöhren, eiersalat).plusTag(bratkartoffeln, bratkartoffeln, bratkartoffeln).erstelle();
        Menu planMuehlheim = new DummySpeiseplan().fuerKantine(Canteen.MUELHEIM_AN_DER_RUHR).plusTag(buttermöhren, buttermöhren, buttermöhren).plusTag(bratkartoffeln, bratkartoffeln, bratkartoffeln).erstelle();
        creator = new PurchaseListCreator();
        creator.setDao(verwaltung);
        creator.add(planEssen);
        creator.add(planMuehlheim);
        
        PurchaseList liste = creator.erzeuge();
        assertEquals(4, liste.getPositionen().size());
        assertEquals("Müller", liste.getPositionen().get(0).getLieferant().getName());
        assertEquals(15.0, liste.getPositionen().get(0).getPreis(), 0.001);
    }
    
    private Menu erzeugeDummySpeiseplan(Canteen kantine) {
        Menu plan = new Menu();
        plan.setKantine(kantine);
        Day tag = erzeugeTag();
        plan.addDay(tag);

        return plan;
    }

    private Day erzeugeTag() {
        Day tag = new Day(1);
        Dish speise = erzeugeBratkartoffeln();
        Dish speise1 = erzeugeBratkartoffeln();
        Dish speise2 = erzeugeBratkartoffeln();
        tag.setBeliebtesteSpeise(speise);
        tag.setZweitbeliebtesteSpeise(speise1);
        tag.setDrittbeliebtesteSpeise(speise2);
        return tag;
    }

    private Dish erzeugeBratkartoffeln() {
        Ingredient kartoffeln = new DummyZutat().name("Kartoffeln").einheit(Unit.STUECK).menge(2.0).kategorie(FoodCategory.VEGETARIAN).erstelle();
        return new DummySpeise().name("Bratkaroffeln").mitZutat(kartoffeln).beliebtheit(1).erstelle();
    }

    protected Dish erzeugeButtermöhren() {
        Ingredient moehren = new DummyZutat().name("Möhren").einheit(Unit.STUECK).menge(2.0).kategorie(FoodCategory.VEGETARIAN).erstelle();
        Dish speise = new DummySpeise().name("Buttermöhren").mitZutat(moehren).erstelle();
        return speise;
    }
    
    protected Dish erzeugeEiersalat() {
        Ingredient moehren = new DummyZutat().name("Eier").einheit(Unit.STUECK).menge(4.0).kategorie(FoodCategory.VEGETARIAN).erstelle();
        Dish speise = new DummySpeise().name("Eiersalat").mitZutat(moehren).erstelle();
        return speise;
    }
}
