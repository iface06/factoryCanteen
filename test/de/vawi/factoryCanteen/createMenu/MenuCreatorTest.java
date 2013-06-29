package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.PeriodeConfiguration;
import de.vawi.factoryCanteen.entities.Offer;
import de.vawi.factoryCanteen.createMenu.MenuCreator;
import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 *
 * @author Tobias
 */
public class MenuCreatorTest {

    private MenuCreator creator;
    private PeriodeConfiguration periode;
    private static Date lastDate;

    @Test
    public void testOfferDays() {
        List<Offer> offers = creator.create();
        assertEquals(periode.getNumberOfDays(), offers.size());

    }

    @Test
    public void testTheirAreNotWeekendOffers() {
        MenuCreationRule rule = new MenuCreationRuleMock();
        List<Offer> offers = creator.create();

        assertFalse(offers.isEmpty());
        for (Offer offer : offers) {
            int weekday = new DateTime(offer.getDate()).getDayOfWeek();
            assertTrue(weekday != DateTimeConstants.SATURDAY && weekday != DateTimeConstants.SUNDAY);
        }
    }

    /**
     * Nachdem die Offers erstellt wurden, sollen diese absteigend nach ihrem
     * Angebotsdatum sortiert werden.
     */
    @Test
    public void testSortingCreatedOffersByDate() {


        List<Offer> offers = creator.create();
        Date previousOfferDate = null;
        for (Offer offer : offers) {
            if (previousOfferDate != null) {
                assertTrue("Falsche Sortierung! Die Liste mit den Offers soll aufsteigend nach ihrem Datum sortiert werden",
                        previousOfferDate.equals(offer.getDate()) || previousOfferDate.before(offer.getDate()));

            }
            previousOfferDate = offer.getDate();
        }

    }

    @Before
    public void before() {
        lastDate = new DateTime().withDate(2013, 5, 7).withTime(0, 0, 0, 0).toDate();
        periode = new PeriodeConfiguration();
        creator = new MenuCreator();
        creator.setDao(new OffersCreatorMockDao());
        creator.setPeriode(periode);
        creator.addRule(new MenuCreationRuleMock());
    }

    public static class OffersCreatorMockDao implements CreateMenuDao {

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

    public static class MenuCreationRuleMock implements MenuCreationRule {

        @Override
        public void execute(List<Offer> offers, Date offerDate) {
            Offer offer = new Offer();
            offer.setDate(offerDate);
            offers.add(offer);
        }

        @Override
        public void setDao(CreateMenuDao dao) {
        }
    }
}
