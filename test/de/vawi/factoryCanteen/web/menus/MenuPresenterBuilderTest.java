/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.web.menus;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Tobias
 */
public class MenuPresenterBuilderTest {

    private List<Offer> offers;

    @Test
    public void testGetMenuRowsForEachOpportunityPerDay() {

        MenuPresenter presenter = new MenuPresenterBuilder().build(offers);

        assertEquals(new PeriodeConfiguration().getNumberOfDishesPerDay(), presenter.getMenuRows().size());
    }

    @Test
    public void testGetOfferFromMenuRow() {

        MenuPresenter presenter = new MenuPresenterBuilder().build(offers);

        for (MenuRow menuRow : presenter.getMenuRows()) {
            assertEquals(new PeriodeConfiguration().getDaysPerWeek(), menuRow.size());

        }
    }

    @Test
    public void testCalendarWeekForMenu() {
        MenuPresenter presenter = new MenuPresenterBuilder().build(offers);
        CalendarWeek week = CalendarWeek.current();

        assertEquals(week, presenter.getCalendarWeek());
    }

    @Test
    public void testWeekdays() {
        MenuPresenter presenter = new MenuPresenterBuilder().build(offers);
        assertEquals(new PeriodeConfiguration().getDaysPerWeek(), presenter.getWeekdays().size());
    }

    @Before
    public void before() {
        offers = new ArrayList<>();
        int requiredDishesPerWeek = new PeriodeConfiguration().getDaysPerWeek() * new PeriodeConfiguration().getNumberOfDishesPerDay();
        for (int i = 0; i < requiredDishesPerWeek; i++) {
            Dish dish = new Dish();
            dish.setName("Dish-" + i);
            dish.setPopularity(i);
            Offer offer = new Offer();
            offer.setDish(dish);
            offer.setDate(new DateTime().withTime(0, 0, 0, 0).plusDays(i % new PeriodeConfiguration().getDaysPerWeek()).toDate());
            offers.add(offer);
        }
    }
}
