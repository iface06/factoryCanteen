package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Tobias
 */
public class NoReplicationRuleTest {

    private List<Offer> wholeOffers;
    private Date offerDate;
    private Offer chickenWings;
    private List<Dish> dishes;
    private CreateMenuDao dao;
    private List<Offer> dailyOffers;
    private NoReplicationRule rule;

    @Before
    public void before() {
        chickenWings = createOffer("Hot Chicken Wings");
        initAlreadySelectedOffersForPeriode();
        mockDao();
        initRule();
    }

    @Test
    public void testEachDishUnique() {
        rule.execute(dailyOffers, offerDate);

        assertFalse(dailyOffers.isEmpty());
        assertFalse(wholeOffers.containsAll(dailyOffers));
    }

    public Offer createOffer(String dishName) {
        Offer offer = new Offer();
        Dish dish = createDish(dishName);
        offer.setDish(dish);
        return offer;
    }

    private Dish createDish(String dishName) {
        Dish dish = new Dish();
        dish.setName(dishName);
        return dish;
    }

    private void initDishesForDao() {
        dishes = new ArrayList<>();
        dishes.add(createDish("Hot Chicken Wings"));
        dishes.add(createDish("Surf and Turf"));
        dishes.add(createDish("Hot Dog"));
        dishes.add(createDish("Beef Burger"));
    }

    private void initAlreadySelectedOffersForPeriode() {
        wholeOffers = new ArrayList<>();
        wholeOffers.add(chickenWings);
        wholeOffers.add(createOffer("Surf and Turf"));
    }

    private void mockDao() {
        initDishesForDao();
        dao = mock(CreateMenuDao.class);
        when(dao.findFavorDishesForPeriode()).thenReturn(dishes);
    }

    private void initRule() {
        dailyOffers = new ArrayList<>();
        offerDate = new DateTime().withTime(0, 0, 0, 0).withDate(2013, 5, 3).toDate();
        rule = new TestableNoReplicationRule();
        rule.setAlreadySelectedOffers(wholeOffers);
        rule.setDao(dao);
    }

    public class TestableNoReplicationRule extends NoReplicationRule {

        @Override
        protected Dish selectDishForOffer(int dishNumber) {
            return dishes.get(dishNumber);
        }

        @Override
        public void setDao(CreateMenuDao dao) {
        }
    }
}
