package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.PeriodeConfiguration;
import de.vawi.factoryCanteen.dummies.DishCreator;
import de.vawi.factoryCanteen.entities.*;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Component Test für das Erstellen von Speiseplänen
 *
 * @author iface06
 */
public class CreateMenusInteractorTest {

    private List<Offer> storedOffers;
    private PeriodeConfiguration periode;
    private CreateMenuDao dao;

    @Before
    public void before() {
        storedOffers = new ArrayList<>();
    }

    @Test
    public void testSomeMethod() {

        createPeriode();
        RequestBoundary<CreateMenusRequest> requestBoundary = createRequestMock();
        createDao();

        CreateMenusInteractor interactor = new CreateMenusInteractor(requestBoundary);
        interactor.setDao(dao);
        interactor.execute();

        assertEquals(2, interactor.getResponse().size());
        assertEquals(2, storedOffers.size());
    }

    private RequestBoundary<CreateMenusRequest> createRequestMock() {

        CreateMenusRequest request = mock(CreateMenusRequest.class);
        when(request.getPeriode()).thenReturn(periode);
        RequestBoundary<CreateMenusRequest> requestBoundary = mock(RequestBoundary.class);
        when(requestBoundary.passRequest()).thenReturn(request);
        return requestBoundary;
    }

    private void createPeriode() {
        periode = new PeriodeConfiguration();
        periode.setNumberOfOfferedDishesPerDay(3);
        periode.setNumberOfDaysPerWeek(5);
        periode.setNumberOfWeek(1);

    }

    private void createDao() {
        final List<Dish> favoriteDishes = createFavoritDishes();
        final List<Dish> unfavoriteDishes = createUnfavoriteDishes();

        dao = new CreateMenuDao() {
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
                return favoriteDishes;
            }

            @Override
            public void storeOffers(List<Offer> offers) {
                storedOffers.addAll(offers);
            }
        };

    }

    private List<Dish> createFavoritDishes() {
        List<Dish> favorites = new ArrayList<>();
        Dish bratkartoffeln = new DishCreator().beliebtheit(1).category(DishCategory.VEGETARIAN).name("Bratkartoffeln").create();
        Dish kartoffelsalat = new DishCreator().beliebtheit(4).category(DishCategory.VEGETARIAN).name("Kartoffelsalat").create();
        Dish pellkartoffeln = new DishCreator().beliebtheit(7).category(DishCategory.VEGETARIAN).name("Pellkartoffeln").create();
        Dish kartoffeln = new DishCreator().beliebtheit(10).category(DishCategory.VEGETARIAN).name("Kartoffeln").create();
        Dish kartoffelEierSalat = new DishCreator().beliebtheit(11).category(DishCategory.VEGETARIAN).name("kartoffelEierSalat").create();
        Dish pommes = new DishCreator().beliebtheit(12).category(DishCategory.VEGETARIAN).name("Pommes").create();
        Dish ofenkartoffeln = new DishCreator().beliebtheit(13).category(DishCategory.VEGETARIAN).name("ofenkartoffeln").create();
        Dish steak = new DishCreator().beliebtheit(2).category(DishCategory.MEAT).name("Pfeffersteaks").create();
        Dish filet = new DishCreator().beliebtheit(5).category(DishCategory.MEAT).name("Filet").create();
        Dish ribEye = new DishCreator().beliebtheit(8).category(DishCategory.MEAT).name("Rib-eye").create();
        Dish rinderKotlett = new DishCreator().beliebtheit(14).category(DishCategory.MEAT).name("rinderKotlett").create();
        Dish rinderNackenSteaks = new DishCreator().beliebtheit(15).category(DishCategory.MEAT).name("rinderNackenSteaks").create();
        Dish knoblauchgarnelen = new DishCreator().beliebtheit(3).category(DishCategory.FISH).name("Knoblauchgarnelen").create();
        Dish garnelenCocktail = new DishCreator().beliebtheit(6).category(DishCategory.FISH).name("garnelenCocktail").create();
        Dish garnelensalat = new DishCreator().beliebtheit(9).category(DishCategory.FISH).name("garnelensalat").create();
        favorites.add(bratkartoffeln);
        favorites.add(kartoffelsalat);
        favorites.add(pellkartoffeln);
        favorites.add(kartoffeln);
        favorites.add(kartoffelEierSalat);
        favorites.add(pommes);
        favorites.add(ofenkartoffeln);
        favorites.add(steak);
        favorites.add(filet);
        favorites.add(ribEye);
        favorites.add(rinderKotlett);
        favorites.add(rinderNackenSteaks);
        favorites.add(knoblauchgarnelen);
        favorites.add(garnelenCocktail);
        favorites.add(garnelensalat);
        return favorites;
    }

    private List<Dish> createUnfavoriteDishes() {
        List<Dish> unfavorites = new ArrayList<>();
        Dish bratkartoffeln = new DishCreator().beliebtheit(4).category(DishCategory.VEGETARIAN).name("Salzkartoffeln").create();
        Dish steak = new DishCreator().beliebtheit(5).category(DishCategory.MEAT).name("Schuhsohlen").create();
        Dish knoblauchgarnelen = new DishCreator().beliebtheit(6).category(DishCategory.FISH).name("Garnelendärme").create();
        unfavorites.add(bratkartoffeln);
        unfavorites.add(steak);
        unfavorites.add(knoblauchgarnelen);
        return unfavorites;
    }
}
