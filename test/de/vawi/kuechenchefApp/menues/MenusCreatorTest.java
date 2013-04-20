package de.vawi.kuechenchefApp.menues;

import de.vawi.kuechenchefApp.entities.Ingredient;
import de.vawi.kuechenchefApp.Periode;
import de.vawi.kuechenchefApp.dishes.*;
import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.entities.FoodCategory;
import de.vawi.kuechenchefApp.menues.MenusCreator.KeineAusreichendeAnzahlAnSpeisen;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class MenusCreatorTest {

    private Periode planungsperiode;
    private List<Dish> beliebtestSpeisen;
    private List<Dish> unbeliebtesteSpeisen;
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
        MenusCreator ersteller = new TestbarerSpeiseplanErsteller();
        ersteller.setPlanungsperiode(planungsperiode);
        List<Menu> plaene = ersteller.erzeuge();


        assertEquals(2, plaene.size());
        assertEquals(Canteen.ESSEN, plaene.get(0).getKantine());
        assertEquals(Canteen.MUELHEIM_AN_DER_RUHR, plaene.get(1).getKantine());
        for (Menu plan : plaene) {
            assertEquals(5, plan.getTageMitGerichten().size());
        }
    }
    
    @Test(expected=KeineAusreichendeAnzahlAnSpeisen.class)
    public void testSpeiseplanErstellungZuWenigVegetarischSpeisen() {
        ausreichendSpeisenVorhanden = false;
        MenusCreator ersteller = new TestbarerSpeiseplanErsteller();
        ersteller.setPlanungsperiode(planungsperiode);
        List<Menu> plaene = ersteller.erzeuge();
        fail();
    }

    private void initialisierePlanunsperiode() {
        planungsperiode = new Periode();
        planungsperiode.setAnzahlWochen(1);
    }

    private void initialisiereBeliebtesteSpeisen() {
        beliebtestSpeisen = new ArrayList<>();
        beliebtestSpeisen.add(erstelleFleischSpeise("Steaks", 1, FoodCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Currywoscht", 2, FoodCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Schnitzel", 3, FoodCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Hänchen", 4, FoodCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Hasenpfeffer", 5, FoodCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Bismarckheringe", 6, FoodCategory.FISH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Calamarie", 7, FoodCategory.FISH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Spagetthi", 8, FoodCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Ofenkäse", 9, FoodCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Rahmspinat", 10, FoodCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Omlette", 11, FoodCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Zwiebelblootz", 12, FoodCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Nürnberger Bratwoscht", 13, FoodCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Bolognese", 14, FoodCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Pilzraviolie", 15, FoodCategory.VEGETARIAN));
    }
    
    private void initialisiereUnbeliebtesteSpeisen() {
        unbeliebtesteSpeisen = new ArrayList<>();
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Pressfleisch", 16, FoodCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Rohei", 17, FoodCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Affenhirntoast", 18, FoodCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Stierhoden", 19, FoodCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Schlange", 20, FoodCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Qualle", 21, FoodCategory.FISH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Sandfisch", 22, FoodCategory.FISH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Straußeneier", 23, FoodCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Tofu", 24, FoodCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Grießbrei", 25, FoodCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Linsensupppe", 26, FoodCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Haferschleim", 27, FoodCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Thüringer Bratwoscht", 28, FoodCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Cabonara", 29, FoodCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Vollkornraviolie", 30, FoodCategory.VEGETARIAN));
    }

    private Dish erstelleFleischSpeise(String name, int beliebtheit, FoodCategory kategorie) {
        Ingredient zutat = new DummyZutat().name(name + "-Zutat").kategorie(kategorie).menge(1).verfuegbareMengeAmMarkt(100000).erstelle();
        Ingredient salz = new DummyZutat().name("Salz").kategorie(FoodCategory.VEGETARIAN).menge(1).verfuegbareMengeAmMarkt(100000).erstelle();
        Dish speise = new DummySpeise().name(name).beliebtheit(beliebtheit).mitZutat(zutat).mitZutat(salz).erstelle();
        sortiereZutatenDerSpeise(speise);
        return speise;
    }

    private void sortiereZutatenDerSpeise(Dish speise) {
        Collections.sort(speise.getZutaten(), new Comparator<Ingredient>() {

            @Override
            public int compare(Ingredient o1, Ingredient o2) {
                return o1.getCategory().compareTo(o2.getCategory());
            }
        });
    }

    private List<Dish> loescheSpeisenMitKategorie(List<Dish> beliebtestSpeisen, FoodCategory kategorie) {
        List<Dish> ohneKategorie = new ArrayList<>();
        for (Dish speise : beliebtestSpeisen) {
            if(!speise.getKategorie().equals(kategorie)){
                ohneKategorie.add(speise);
            }
        }
        return ohneKategorie;
    }

    

    class TestbarerSpeiseplanErsteller extends MenusCreator {
        

        @Override
        protected List<Dish> findeBeliebtesteSpeisenFuerPlanungsperiode() {
            return beliebtestSpeisen;
        }

        @Override
        protected List<Dish> findeUnbeliebtesteSpeisen() {
            return unbeliebtesteSpeisen;
        }

        @Override
        protected boolean sindAusreichendSpeisenInSpeisenVerwaltungVorhanden() {
            return ausreichendSpeisenVorhanden;
        }
        
        
    }
}
