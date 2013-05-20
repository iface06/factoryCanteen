package de.vawi.kuechenchefApp.createMenu;

import de.vawi.kuechenchefApp.entities.Canteen;
import de.vawi.kuechenchefApp.entities.Menu;
import de.vawi.kuechenchefApp.entities.Periode;
import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.entities.DishCategory;
import de.vawi.kuechenchefApp.createMenu.MenusCreator.KeineAusreichendeAnzahlAnSpeisen;
import de.vawi.kuechenchefApp.dummies.*;
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
     * 45 beliebtesten Gerichte sich holt davon 3 Fischgerichte davon 15
     * vegetarische Rest Fleisch Waren müssen ausreichend verfügbar sein
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

    @Test(expected = KeineAusreichendeAnzahlAnSpeisen.class)
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
        beliebtestSpeisen.add(erstelleFleischSpeise("Steaks", 1, DishCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Currywoscht", 2, DishCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Schnitzel", 3, DishCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Hänchen", 4, DishCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Hasenpfeffer", 5, DishCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Bismarckheringe", 6, DishCategory.FISH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Calamarie", 7, DishCategory.FISH));
        beliebtestSpeisen.add(erstelleFleischSpeise("Spagetthi", 8, DishCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Ofenkäse", 9, DishCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Rahmspinat", 10, DishCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Omlette", 11, DishCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Zwiebelblootz", 12, DishCategory.VEGETARIAN));
        beliebtestSpeisen.add(erstelleFleischSpeise("Nürnberger Bratwoscht", 13, DishCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Bolognese", 14, DishCategory.MEAT));
        beliebtestSpeisen.add(erstelleFleischSpeise("Pilzraviolie", 15, DishCategory.VEGETARIAN));
    }

    private void initialisiereUnbeliebtesteSpeisen() {
        unbeliebtesteSpeisen = new ArrayList<>();
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Pressfleisch", 16, DishCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Rohei", 17, DishCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Affenhirntoast", 18, DishCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Stierhoden", 19, DishCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Schlange", 20, DishCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Qualle", 21, DishCategory.FISH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Sandfisch", 22, DishCategory.FISH));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Straußeneier", 23, DishCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Tofu", 24, DishCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Grießbrei", 25, DishCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Linsensupppe", 26, DishCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Haferschleim", 27, DishCategory.VEGETARIAN));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Thüringer Bratwoscht", 28, DishCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Cabonara", 29, DishCategory.MEAT));
        unbeliebtesteSpeisen.add(erstelleFleischSpeise("Vollkornraviolie", 30, DishCategory.VEGETARIAN));
    }

    private Dish erstelleFleischSpeise(String name, int beliebtheit, DishCategory kategorie) {
        return new DishCreator().name(name).beliebtheit(beliebtheit).category(DishCategory.MEAT).create();

    }

    private List<Dish> loescheSpeisenMitKategorie(List<Dish> beliebtestSpeisen, DishCategory kategorie) {
        List<Dish> ohneKategorie = new ArrayList<>();
        for (Dish speise : beliebtestSpeisen) {
            if (!speise.getCategory().equals(kategorie)) {
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
