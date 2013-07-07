package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.junit.*;
import static org.junit.Assert.*;

public class EveryDayDishMenuRuleTest {

    List<Offer> offers;
    private EveryDayDishMenuRule rule;
    private Date offerDate;
    private List<Offer> wholeOffers;

    @Test
    public void testThatEveryDayOffersMeatDish() {

        initRuleFor(DishCategory.MEAT);

        rule.execute(offers, offerDate);

        assertEquals(1, offers.size());
        assertDishCategoryIs(DishCategory.MEAT);
    }

    @Test
    public void testThatEveryDayOffersVegetarianDish() {

        initRuleFor(DishCategory.VEGETARIAN);
        rule.execute(offers, offerDate);

        assertEquals(1, offers.size());
        assertDishCategoryIs(DishCategory.VEGETARIAN);

    }

    @Before
    public void before() {
        offers = new ArrayList<>();
        offerDate = new DateTime().withDate(2013, 7, 1).withTime(0, 0, 0, 0).toDate();
        wholeOffers = new ArrayList<>();

    }

    private void initRuleFor(DishCategory category) {
        rule = new EveryDayDishMenuRule(category);
        rule.setDao(new OfferCreatorDao());
        rule.setAlreadySelectedOffers(wholeOffers);
    }

    private void assertDishCategoryIs(DishCategory category) {
        boolean isRequiredCategory = true;
        for (Offer offer : offers) {
            if (!offer.getDishCategory().equals(category)) {
                isRequiredCategory = false;
            }
        }
        assertTrue("Contains a dish with a not required category!", isRequiredCategory);
    }

    private static class OfferCreatorDao implements CreateMenuDao {

        @Override
        public Date findDateOfLastOffer() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Dish> findDishesByCategory(DishCategory category) {
            List<Dish> dishes = new ArrayList<>();
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
        public List<Dish> findFavorDishesForPeriode() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void storeOffers(List<Offer> offers) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
