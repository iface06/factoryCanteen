package de.vawi.factoryCanteen.persistence.dishes;

import de.vawi.factoryCanteen.persistence.dishes.DishesDB;
import de.vawi.factoryCanteen.entities.Dish;
import de.vawi.factoryCanteen.entities.DishCategory;
import de.vawi.factoryCanteen.persistence.dishes.DishesDB.SpeiseNichtGefunden;
import org.junit.*;
import static org.junit.Assert.*;

public class DishesDBTest {

    private DishesDB verwaltung;

    @Before
    public void before() {
        verwaltung = new DishesDB();

    }

    @Test
    public void testHinzufuegenEinerSpeise() {
        Dish speise = erstelleSpeise("Steaks");
        verwaltung.addDish(speise);
        assertEquals(1, verwaltung.numberOfDishes());
    }

    @Test
    public void testHinzufuegenEinerBereitsVorhandenSpeise() {
        Dish speise = erstelleSpeise("Steaks");
        verwaltung.addDish(speise);

        assertEquals(1, verwaltung.numberOfDishes());
    }

    @Test
    public void testFindeSpeiseMitNamen() {
        Dish speise = erstelleSpeise("Steaks");
        verwaltung.addDish(speise);
        Dish steaks = verwaltung.findDishByName("Steaks");

        assertEquals("Steaks", steaks.getName());
    }

    @Test(expected = SpeiseNichtGefunden.class)
    public void testFindeNichtDieSpeiseMitNamen() {
        Dish steaks = verwaltung.findDishByName("Drei im Weckla");
        fail();
    }

    private Dish erstelleSpeise(String name) {
        Dish speise = new Dish();
        speise.setName(name);
        return speise;
    }

    private void fuegeSpeisenNachKategorieInVerwaltungEin(DishCategory kategorie, int anzahl) {
        for (int anzahlSpeisen = 0; anzahlSpeisen < anzahl; anzahlSpeisen++) {
            Dish speise = new DummyDish().name("Speise-" + kategorie.getAbbrevation() + "-" + anzahlSpeisen).category(kategorie).create();
            verwaltung.addDish(speise);
        }
    }
}
