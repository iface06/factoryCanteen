/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.entities;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iface06
 */
public class DayTest {

    public DayTest() {
    }

    @Test
    public void testAddDishForDay() {
        Dish offer = createDish(1, "Schnitzel mit Pommes");
        Day day = new Day(new Date());
        day.insert(offer);
        assertTrue(day.iterator().hasNext());
    }

    @Test
    public void testOrderingOfDishesBasedOn() {
        Dish schnitzel = createDish(1, "Schnitzel mit Pommes");
        Dish curryWurst = createDish(2, "Currywurst mit Pommes");
        Dish leberkaese = createDish(3, "Leberkäse mit Kartoffelsalat");
        Day day = new Day(new Date());
        day.insert(curryWurst);
        day.insert(schnitzel);
        day.insert(leberkaese);

        assertDishOrderBasedOnPopularity(day);
    }

    public Dish createDish(int popularity, String name) {
        Dish offer = new Dish();
        offer.setPopularity(popularity);
        offer.setName(name);
        return offer;
    }

    public void assertDishOrderBasedOnPopularity(Day day) {
        Integer expectedPopularity = 1;
        for (Dish dish : day) {
            assertEquals(expectedPopularity, dish.getPopularity());
            expectedPopularity++;
        }
    }
}
