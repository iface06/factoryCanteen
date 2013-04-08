package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.PlanungsPeriode;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import de.vawi.kuechenchefApp.speisen.*;
import de.vawi.kuechenchefApp.speiseplan.SpeiseplanErsteller.KeineAusreichendeAnzahlAnSpeisen;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class SpeiseplanErstellerTest {

    private PlanungsPeriode planungsperiode;
    private List<Speise> beliebtestSpeisen;
    private List<Speise> unbeliebtesteSpeisen;
    private boolean ausreichendSpeisenVorhanden;

    @Before
    public void before() {
        ausreichendSpeisenVorhanden = true;
        initialisierePlanunsperiode();
        initialisiereBeliebtesteSpeisen();
        initialisiereUnbeliebtesteSpeisen();
    }

    /**
     * 45 beliebtesten Gerichte sich holt
     * davon 3 Fischgerichte
     * davon 15 vegetarische
     * Rest Fleisch
     * Waren müssen ausreichend verfügbar sein
     *
     */
    @Test
    public void testSpeiseplanErstellungOhneVerfügbarkeitsProbleme() {
        SpeiseplanErsteller ersteller = new TestbarerSpeiseplanErsteller();
        ersteller.setPlanungsperiode(planungsperiode);
        List<Speiseplan> plaene = ersteller.erzeuge();


        assertEquals(2, plaene.size());
        assertEquals(Kantine.ESSEN, plaene.get(0).getKantine());
        assertEquals(Kantine.MUELHEIM_AN_DER_RUHR, plaene.get(1).getKantine());
        for (Speiseplan plan : plaene) {
            assertEquals(5, plan.getTageMitGerichten().size());
        }
    }
    
    @Test(expected=KeineAusreichendeAnzahlAnSpeisen.class)
    public void testSpeiseplanErstellungZuWenigVegetarischSpeisen() {
        ausreichendSpeisenVorhanden = false;
        SpeiseplanErsteller ersteller = new TestbarerSpeiseplanErsteller();
        ersteller.setPlanungsperiode(planungsperiode);
        List<Speiseplan> plaene = ersteller.erzeuge();
        fail();
    }

    private void initialisierePlanunsperiode() {
        planungsperiode = new PlanungsPeriode();
        planungsperiode.setAnzahlWochen(1);
    }

    private void initialisiereBeliebtesteSpeisen() {
        beliebtestSpeisen = new ArrayList<>();
        beliebtestSpeisen.add(erstelleFleischSpeise("Steaks", 1, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Currywoscht", 2, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Schnitzel", 3, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Hänchen", 4, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Hasenpfeffer", 5, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Bismarckheringe", 6, SpeisenUndNahrungsmittelKategorie.FISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Calamarie", 7, SpeisenUndNahrungsmittelKategorie.FISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Spagetthi", 8, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Ofenkäse", 9, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Rahmspinat", 10, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Omlette", 11, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Zwiebelblootz", 12, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Nürnberger Bratwoscht", 13, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Bolognese", 14, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Pilzraviolie", 15, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
    }
    
    private void initialisiereUnbeliebtesteSpeisen() {
        unbeliebtesteSpeisen = new ArrayList<>();
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Pressfleisch", 16, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Rohei", 17, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Affenhirntoast", 18, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Stierhoden", 19, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Schlange", 20, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Qualle", 21, SpeisenUndNahrungsmittelKategorie.FISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Sandfisch", 22, SpeisenUndNahrungsmittelKategorie.FISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Straußeneier", 23, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Tofu", 24, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Grießbrei", 25, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Linsensupppe", 26, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Haferschleim", 27, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Thüringer Bratwoscht", 28, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Cabonara", 29, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Vollkornraviolie", 30, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
    }

    private Speise erstelleFleischSpeise(String name, int beliebtheit, SpeisenUndNahrungsmittelKategorie kategorie) {
        Zutat zutat = new DummyZutat().name(name + "-Zutat").kategorie(kategorie).menge(1).verfuegbareMengeAmMarkt(100000).erstelle();
        Zutat salz = new DummyZutat().name("Salz").kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).menge(1).verfuegbareMengeAmMarkt(100000).erstelle();
        Speise speise = new DummySpeise().name(name).beliebtheit(beliebtheit).mitZutat(zutat).mitZutat(salz).erstelle();
        sortiereZutatenDerSpeise(speise);
        return speise;
    }

    private void sortiereZutatenDerSpeise(Speise speise) {
        Collections.sort(speise.getZutaten(), new Comparator<Zutat>() {

            @Override
            public int compare(Zutat o1, Zutat o2) {
                return o1.getKategorie().compareTo(o2.getKategorie());
            }
        });
    }

    private List<Speise> loescheSpeisenMitKategorie(List<Speise> beliebtestSpeisen, SpeisenUndNahrungsmittelKategorie kategorie) {
        List<Speise> ohneKategorie = new ArrayList<>();
        for (Speise speise : beliebtestSpeisen) {
            if(!speise.getKategorie().equals(kategorie)){
                ohneKategorie.add(speise);
            }
        }
        return ohneKategorie;
    }

    

    class TestbarerSpeiseplanErsteller extends SpeiseplanErsteller {
        

        @Override
        protected List<Speise> findeBeliebtesteSpeisenFuerPlanungsperiode() {
            return beliebtestSpeisen;
        }

        @Override
        protected List<Speise> findeUnbeliebtesteSpeisen() {
            return unbeliebtesteSpeisen;
        }

        @Override
        protected boolean sindAusreichendSpeisenInSpeisenVerwaltungVorhanden() {
            return ausreichendSpeisenVorhanden;
        }
        
        
    }
}