/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.Periode;
import de.vawi.factoryCanteen.entities.Offer;
import de.vawi.factoryCanteen.entities.Menu;
import de.vawi.factoryCanteen.entities.Dish;
import de.vawi.factoryCanteen.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.createMenu.OffersCreator;
import de.vawi.factoryCanteen.entities.DishCategory;
import de.vawi.factoryCanteen.entities.DishCategoryTest;
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
        assertEquals(new Periode().getNumberOfDays(), countedDaysOfPeriode);

    }

    @Test
    public void testForEveryDayTheirIsAMeatDish() {
        List<Offer> offers = creator.create();
        int countedMeatDishes = 0;
        Date date = null;
        for (Offer offer : offers) {
            if ((date == null || !date.equals(offer.getDate()))
                    && offer.getDishCategory().equals(DishCategory.MEAT)) {
                date = offer.getDate();
                countedMeatDishes++;
            }
        }
        assertEquals(new Periode().calculateNumberOfRequiredMeatDishes(), countedMeatDishes);
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
        public List<Dish> findFavorDishesForPeriode(Periode periode) {
            List<Dish> dishes = new ArrayList<>();
            List<DishCategory> categories = Arrays.asList(DishCategory.values());
            for (int i = 0; i < periode.calculateRequiredMealsForPeriode(); i++) {
                Dish d = new Dish();
                d.setName("Dish-" + i);
                d.setPopularity(i);
                d.setCategory(categories.get(i % 3));
                dishes.add(d);
            }

            return dishes;
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
