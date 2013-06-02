package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.junit.*;
import static org.junit.Assert.*;

public class EveryDayDishMenuRuleTest {

    List<Offer> offers;
    private Periode periode;
    private EveryDayDishMenuRule rule;

    @Test
    public void testThatEveryDayOffersMeatDish() {

        rule.setDishCategory(DishCategory.MEAT);
        rule.execute();

        assertEquals(15, offers.size());
        assertDishCategoryIs(DishCategory.MEAT);
    }

    @Test
    public void testThatEveryDayOffersVegetarianDish() {

        rule.setDishCategory(DishCategory.VEGETARIAN);
        rule.execute();

        assertEquals(15, offers.size());
        assertDishCategoryIs(DishCategory.VEGETARIAN);

    }

    @Before
    public void before() {
        offers = new ArrayList<>();
        periode = new Periode();

        initRule();
    }

    private void initRule() {
        rule = new EveryDayDishMenuRule();
        rule.setDao(new OfferCreatorDao());
        rule.setOffers(offers);
        rule.setPeriode(periode);
        rule.setStartDate(new DateTime().withDate(2012, 12, 31).withTime(0, 0, 0, 0).toDate());
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
        public List<Dish> findFavorDishesForPeriode(Periode periode) {
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

        @Override
        public List<Dish> findDishesByCategory(DishCategory category) {
            List<Dish> dishes = new ArrayList<>();
            for (int i = 0; i < new Periode().calculateRequiredMealsForPeriode(); i++) {
                Dish d = new Dish();
                d.setName("Dish-" + i);
                d.setPopularity(i);
                d.setCategory(category);
                dishes.add(d);
            }

            return dishes;
        }
    }
}
