/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.createMenu;

import de.vawi.kuechenchefApp.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Tobias
 */
public class OffersForNextPeriodeCreatorTest {

    private OffersCreator creator;
    private Periode periode;

    @Test
    public void testCreateOffersForPeriode() {
        List<Offer> offers = creator.create();

        assertEquals(new Periode().quantityOfRequiredDishes(), offers.size());
    }

    @Test
    public void testEnoughDishesForPeriode() {
        List<Offer> offers = creator.create();

        assertEquals(new Periode().quantityOfRequiredDishes(), offers.size());
    }

    @Test
    public void testTakeCareThatEachDayHasAOffer() {
        List<Offer> offers = creator.create();
        int countedDaysOfPeriode = 0;
        Date date = null;
        for (Offer offer : offers) {
            if (date == null || !date.equals(offer.getDate())) {
                date = offer.getDate();
                countedDaysOfPeriode++;
            }

        }
        assertEquals(new Periode().numberOfDays(), countedDaysOfPeriode);

    }

    public Periode initPeriode() {
        Periode periode = new Periode();
        periode.setNumberOfOfferedDishesPerDay(3);
        periode.setNumberOfDaysPerWeek(5);
        periode.setNumberOfWeek(3);
        return periode;
    }

    @Before
    public void initOffersCreator() {
        periode = new Periode();
        periode.setNextStartDate(new DateTime().withDate(2013, 1, 1).withTime(0, 0, 0, 0).toDate());
        creator = new OffersCreator();
        creator.setPeriode(periode);
        creator.setDao(new OfferCreatorDao());
    }

    private static class OfferCreatorDao implements CreateMenuDao {

        @Override
        public List<Dish> findeBeliebtesteSpeisenFuerPlanungsPeriode(Periode periode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Dish> findeUnbeliebtesteSpeisen(Periode periode) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean areEnoughtDishesAvailable() {
            return true;
        }

        @Override
        public void storeMenues(List<Menu> menues) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
