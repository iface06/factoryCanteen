package de.vawi.kuechenchefApp.dishes;

import de.vawi.kuechenchefApp.dishes.IngredientCalculator;
import de.vawi.kuechenchefApp.entities.*;
import de.vawi.kuechenchefApp.menues.Canteen;
import de.vawi.kuechenchefApp.menues.Menu;
import de.vawi.kuechenchefApp.menues.Day;
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
        IngredientCalculator kalkulator = new IngredientCalculator();
        Map<Food, Double> mengen = kalkulator.berechneGesamtMengen(plan);

        Food kartoffeln = new Food();
        kartoffeln.setName("Kartoffeln");

        assertEquals(1, mengen.size());
        assertEquals(1502.0, mengen.get(kartoffeln), 0.0001);
    }
    
    private Menu erzeugeDummySpeiseplan() {
        List<Day> tage = new ArrayList<Day>();
        Day tag = erzeugeTag();
        tage.add(tag);
        Menu plan = new Menu(Canteen.ESSEN, tage);

        return plan;
    }

    private Day erzeugeTag() {
        Day tag = new Day(1);
        Dish speise = erzeugeSpeise();
        Dish speise1 = erzeugeSpeise();
        Dish speise2 = erzeugeSpeise();
        tag.setBeliebtesteSpeise(speise);
        tag.setZweitbeliebtesteSpeise(speise1);
        tag.setDrittbeliebtesteSpeise(speise2);
        return tag;
    }

    private Dish erzeugeSpeise() {
        Dish speise = new Dish();
        Ingredient zutat = erzeugeZutat();
        speise.addZutat(zutat);
        return speise;
    }

    private Ingredient erzeugeZutat() {
        Ingredient zutat = new Ingredient();
        Food nahrungsmittel = erzeugeNahrungsmittel(zutat);
        zutat.setMenge(2.0);
        zutat.setNahrungsmittel(nahrungsmittel);
        return zutat;
    }

    private Food erzeugeNahrungsmittel(Ingredient zutat) {
        Food nahrungsmittel = new Food();
        nahrungsmittel.setName("Kartoffeln");
        nahrungsmittel.setEinheit(Unit.STUECK);
        return nahrungsmittel;
    }
}
