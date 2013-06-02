/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.PeriodeConfiguration;
import de.vawi.factoryCanteen.entities.Offer;
import de.vawi.factoryCanteen.createMenu.OffersCreator;
import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 *
 * @author Tobias
 */
public class OffersForNextPeriodeCreatorTest {

    private OffersCreator creator;
    private PeriodeConfiguration periode;
    private static Date lastDate;

    @Test
    public void testCreateOffersForPeriode() {
        MenuCreationRule rule = mock(MenuCreationRule.class);

        creator = new OffersCreator();
        creator.setDao(new OffersCreatorDao());
        creator.setPeriode(periode);
        creator.addRule(rule);
        creator.addRule(rule);
        creator.addRule(rule);

        List<Offer> offers = creator.create();

        verify(rule, times(3)).setDao(any(CreateMenuDao.class));
        verify(rule, times(3)).setPeriode(any(PeriodeConfiguration.class));
        verify(rule, times(3)).setStartDate(any(Date.class));
        verify(rule, times(3)).execute(any(List.class));
    }

    @Test
    public void testStartDateOfPeriode() {
        lastDate = new DateTime().withDate(2013, 5, 7).withTime(0, 0, 0, 0).toDate();
        MenuCreationRule rule = mock(MenuCreationRule.class);
        creator = new OffersCreator();
        creator.setDao(new OffersCreatorDao());
        creator.setPeriode(periode);
        creator.addRule(rule);

        List<Offer> offers = creator.create();

        ArgumentCaptor<Date> argument = ArgumentCaptor.forClass(Date.class);
        verify(rule).setStartDate(argument.capture());
        assertEquals(new DateTime().withDate(2013, 05, 13).withTime(0, 0, 0, 0).toDate(), argument.getValue());
    }

    public static class OffersCreatorDao implements CreateMenuDao {

        @Override
        public List<Dish> findDishesByCategory(DishCategory category) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Date findDateOfLastOffer() {
            return lastDate;
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
