package de.vawi.factoryCanteen.persistence.offers;

import de.vawi.factoryCanteen.app.entities.*;
import de.vawi.factoryCanteen.persistence.offers.OffersDB;
import java.io.*;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.*;
import static org.junit.Assert.*;

public class OffersDBTest {

    private OffersDB db;

    @Before
    public void beforeClass() {
        db = OffersDB.getInstance();
        db.rest();
    }

    @Test
    public void testSingelton() {
        OffersDB manager1 = OffersDB.getInstance();
        OffersDB manager2 = OffersDB.getInstance();

        assertEquals(manager1, manager2);
    }

    @Test
    public void testStoringOffers() {
        List<Offer> offers = new ArrayList<>();
        Dish d = createDish();
        Date offerDate = new DateTime().withDate(2012, 12, 31).withTime(0, 0, 0, 0).toDate();
        Offer offer = createOffer(d, offerDate);
        offers.add(offer);

        int sizeBefore = db.offers.size();
        db.storeOffers(offers);
        int sizeAfter = db.offers.size();
        assertTrue(sizeAfter == sizeBefore + 1);
    }

    @Test
    public void testFindLastOffer() {
        Date offerDate1 = new DateTime().withDate(2012, 12, 31).withTime(0, 0, 0, 0).toDate();
        Offer firstOffer = createOffer(new Dish(), offerDate1);

        Date offerDate2 = new DateTime().withDate(2013, 1, 1).withTime(0, 0, 0, 0).toDate();
        Offer lastOffer = createOffer(new Dish(), offerDate2);

        db.storeOffers(Arrays.asList(firstOffer, lastOffer));

        Date lastOfferDate = db.findDateOfLastOffer();

        assertEquals(lastOffer.getDate(), lastOfferDate);
    }

    @Test
    public void test() {
        DateTime offerDate = new DateTime().withTime(0, 0, 0, 0).withDayOfWeek(DateTimeConstants.MONDAY);
        List<Offer> offers = new ArrayList<>();
        for (int days = 0; days < new PeriodeConfiguration().getDaysPerWeek(); days++) {
            Offer offer = createOffer(new Dish(), offerDate.plusDays(days).toDate());
            offers.add(offer);
        }
        db.storeOffers(offers);

        CalendarWeek week = CalendarWeek.current();
        List<Offer> currentOffers = db.findOffersForCalendarWeek(week);
        assertEquals(5, currentOffers.size());


    }

    private Offer createOffer(Dish dish, Date date) {
        Offer o = new Offer();
        o.setDate(date);
        o.setDish(dish);
        return o;
    }

    public Dish createDish() {
        Dish d = new Dish();
        d.setCategory(DishCategory.MEAT);
        d.setName("Schnipo");
        d.setPopularity(1);
        return d;
    }
}
