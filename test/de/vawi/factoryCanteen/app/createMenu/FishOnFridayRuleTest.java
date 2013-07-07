/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;
import org.joda.time.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author iface06
 */
public class FishOnFridayRuleTest {

    private static List<Dish> dishes;
    private List<Offer> offers;
    private List<Offer> wholeOffers;
    private FishOnFridayRule rule;
    private Date offerDate;

    @Test
    public void testItIsFriday() {
        offerDate = new DateTime().withDayOfWeek(DateTimeConstants.FRIDAY).toDate();
        rule.execute(offers, offerDate);
        assertEquals(1, offers.size());
    }

    @Test
    public void testItIsNotFriday() {
        offerDate = new DateTime().withDayOfWeek(DateTimeConstants.MONDAY).toDate();
        rule.execute(offers, offerDate);
        assertTrue(offers.isEmpty());
    }

    @Test
    public void testEveryFridayAnOtherFishMeal() {
        offerDate = new DateTime().withDayOfWeek(DateTimeConstants.FRIDAY).toDate();
        Offer offer = new Offer();
        offer.setDish(dishes.get(0));
        offer.setDate(offerDate);
        offers.add(offer);

        rule.execute(offers, offerDate);
        assertThat(offers.size(), is(2));
        assertThat(offers.get(1), not(offer));

    }

    @Before
    public void before() {
        offers = new ArrayList<>();
        wholeOffers = new ArrayList<>();
        initRule();
    }

    private void initRule() {
        rule = new FishOnFridayRule();
        rule.setDao(new OfferCreatorDao());
        rule.setAlreadySelectedOffers(wholeOffers);

    }

    private static class OfferCreatorDao implements CreateMenuDao {

        @Override
        public List<Dish> findFavorDishesForPeriode() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void storeOffers(List<Offer> offers) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Dish> findDishesByCategory(DishCategory category) {
            dishes = new ArrayList<>();
            for (int i = 0; i < new PeriodeConfiguration().calculateRequiredMealsForPeriode(); i++) {
                Dish d = new Dish();
                d.setName("Dish-" + i);
                d.setPopularity(i);
                d.setCategory(category);
                dishes.add(d);
            }

            return dishes;
        }

        @Override
        public Date findDateOfLastOffer() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
