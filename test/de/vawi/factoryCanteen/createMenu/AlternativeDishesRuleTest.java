/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author iface06
 */
public class AlternativeDishesRuleTest {

    private List<Offer> offers;
    private static List<Dish> dishes;
    private Date startDate;
    private AlternativeDihesRule rule;

    @Test
    public void test() {
        initRule();
        rule.execute(offers);

        assertEquals(15, offers.size());
    }

    @Test
    public void testAddNoDishIfNoMoreRequired() {
        addDayWith3DishesToOffers();
        rule.execute(offers);

        assertEquals("3 Dishes already added to the first day, so only 14 alternatives are required. (3 + 14 = 17)",
                17, offers.size());
    }

    @Test
    public void testNoDishIsMultiply() {
        offers.add(createOffer("Dish-0", startDate));
        rule.execute(offers);

        assertThatNoDishIsMultiply();
    }

    @Test(expected = NotEnoughDishesForMenuCreationAvailable.class)
    public void testNotEnoughDishesAvailable() {
        dishes = new ArrayList<>();
        rule.execute(offers);
    }

    private void assertThatNoDishIsMultiply() {
        boolean isNotMultiply = true;
        for (Offer offer : offers) {
            Collection<Offer> withoutCurrentOffer = copyListWithoutGivenOffer(offer);
            for (Offer offer1 : withoutCurrentOffer) {
                if (offer1.getDish().equals(offer.getDish())) {
                    isNotMultiply = false;
                }
            }
        }
        assertTrue(isNotMultiply);
    }

    @Before
    public void before() {
        startDate = new DateTime().withDate(2012, 12, 31).withTime(0, 0, 0, 0).toDate();
        offers = new ArrayList<>();
        initRule();
        initDishes();
    }

    public void initRule() {
        rule = new AlternativeDihesRule();
        rule.setPeriode(new PeriodeConfiguration());
        rule.setDao(new OfferCreatorDao());
        rule.setStartDate(startDate);
    }

    private void initDishes() {
        dishes = new ArrayList<>();
        List<DishCategory> categories = Arrays.asList(DishCategory.values());
        for (int i = 0; i < new PeriodeConfiguration().calculateRequiredMealsForPeriode(); i++) {
            Dish d = new Dish();
            d.setName("Dish-" + i);
            d.setPopularity(i);
            d.setCategory(categories.get(i % 3));
            dishes.add(d);
        }
        Collections.sort(dishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getPopularity().compareTo(o2.getPopularity());
            }
        });
    }

    private void addDayWith3DishesToOffers() {

        Offer offer = createOffer("Dish A", startDate);
        offers.add(offer);
        offers.add(offer);
        offers.add(offer);
    }

    private Offer createOffer(String name, Date day) {
        Offer offer = new Offer();
        offer.setDate(day);
        Dish d = new Dish();
        d.setName(name);
        offer.setDish(d);
        return offer;
    }

    private Collection<Offer> copyListWithoutGivenOffer(Offer offer) {
        Collection<Offer> withoutCurrentOffer = new ArrayList<>(offers);
        withoutCurrentOffer.remove(offer);
        return withoutCurrentOffer;
    }

    private static class OfferCreatorDao implements CreateMenuDao {

        @Override
        public Date findDateOfLastOffer() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Dish> findDishesByCategory(DishCategory category) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        }

        @Override
        public List<Dish> findFavorDishesForPeriode() {
            return dishes;
        }

        @Override
        public void storeOffers(List<Offer> offers) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
