/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author iface06
 */
public class ThereIsFishOnFridayRuleTest {

    List<Offer> offers;
    private ThereIsFishOnFridayRule rule;

    @Test
    public void testRequiredNumberOfFishDishes() {

        rule.execute(offers);
        assertEquals(new PeriodeConfiguration().calculateRequiredFishDishes(), offers.size());
        assertDishCategoryIs(DishCategory.FISH);
    }

    @Test
    public void testRequiredNumberOfFishDishesIsOfferedOnFriday() {

        rule.execute(offers);
        assertEachFishDishIsOfferedOnFriday();
    }

    @Before
    public void before() {
        offers = new ArrayList<>();
        initRule();
    }

    private void initRule() {
        rule = new ThereIsFishOnFridayRule();

        rule.setDao(new OfferCreatorDao());
        rule.setStartDate(new DateTime().withDate(2012, 12, 31).withTime(0, 0, 0, 0).toDate());
        rule.setPeriode(new PeriodeConfiguration());

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

    private void assertEachFishDishIsOfferedOnFriday() {
        boolean offeredOnEachFriday = true;
        for (Offer offer : offers) {
            if (!(new DateTime(offer.getDate()).getDayOfWeek() == DateTimeConstants.FRIDAY)) {
                offeredOnEachFriday = false;
            }
        }
        assertTrue("Fish dish is not offered on a Friday!", offeredOnEachFriday);
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
        public Date findDateOfLastOffer() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
