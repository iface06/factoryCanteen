package de.vawi.kuechenchefApp.createMenues;

import de.vawi.kuechenchefApp.dummies.DummyZutat;
import de.vawi.kuechenchefApp.dummies.DummySpeise;
import de.vawi.kuechenchefApp.interactorspec.RequestBoundary;
import de.vawi.kuechenchefApp.entities.Menu;
import de.vawi.kuechenchefApp.*;
import de.vawi.kuechenchefApp.entities.Dish;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Component Test für das Erstellen von Speiseplänen
 *
 * @author iface06
 */
public class CreateMenusInteractorTest {

    private List<Menu> storedMenues;
    private Periode periode;
    private CreateMenuDao dao;

    @Before
    public void before() {
        storedMenues = new ArrayList<>();
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
        assertEquals(2, storedMenues.size());
    }

    private RequestBoundary<CreateMenusRequest> createRequestMock() {

        CreateMenusRequest request = mock(CreateMenusRequest.class);
        when(request.getPeriode()).thenReturn(periode);
        RequestBoundary<CreateMenusRequest> requestBoundary = mock(RequestBoundary.class);
        when(requestBoundary.passRequest()).thenReturn(request);
        return requestBoundary;
    }

    private void createPeriode() {
        periode = new Periode();
        periode.setAnzahlGerichteProTag(3);
        periode.setAnzahlTageProWoche(5);
        periode.setAnzahlWochen(1);

    }

    private void createDao() {
        final List<Dish> favoriteDishes = createFavoritDishes();
        final List<Dish> unfavoriteDishes = createUnfavoriteDishes();

        dao = new CreateMenuDao() {
            @Override
            public List<Dish> findeBeliebtesteSpeisenFuerPlanungsPeriode(Periode periode) {
                return favoriteDishes;
            }

            @Override
            public List<Dish> findeUnbeliebtesteSpeisen(Periode periode) {
                return unfavoriteDishes;
            }

            @Override
            public boolean sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden() {
                return true;
            }

            @Override
            public void storeMenues(List<Menu> menues) {
                storedMenues.addAll(menues);
            }
        };

    }

    private List<Dish> createFavoritDishes() {
        List<Dish> favorites = new ArrayList<>();
        Dish bratkartoffeln = new DummySpeise().beliebtheit(1).mitZutat(DummyZutat.kartoffeln()).name("Bratkartoffeln").erstelle();
        Dish kartoffelsalat = new DummySpeise().beliebtheit(4).mitZutat(DummyZutat.kartoffeln()).name("Kartoffelsalat").erstelle();
        Dish pellkartoffeln = new DummySpeise().beliebtheit(7).mitZutat(DummyZutat.kartoffeln()).name("Pellkartoffeln").erstelle();
        Dish kartoffeln = new DummySpeise().beliebtheit(10).mitZutat(DummyZutat.kartoffeln()).name("Kartoffeln").erstelle();
        Dish kartoffelEierSalat = new DummySpeise().beliebtheit(11).mitZutat(DummyZutat.kartoffeln()).name("kartoffelEierSalat").erstelle();
        Dish pommes = new DummySpeise().beliebtheit(12).mitZutat(DummyZutat.kartoffeln()).name("Pommes").erstelle();
        Dish ofenkartoffeln = new DummySpeise().beliebtheit(13).mitZutat(DummyZutat.kartoffeln()).name("ofenkartoffeln").erstelle();
        Dish steak = new DummySpeise().beliebtheit(2).mitZutat(DummyZutat.steaks()).name("Pfeffersteaks").erstelle();
        Dish filet = new DummySpeise().beliebtheit(5).mitZutat(DummyZutat.steaks()).name("Filet").erstelle();
        Dish ribEye = new DummySpeise().beliebtheit(8).mitZutat(DummyZutat.steaks()).name("Rib-eye").erstelle();
        Dish rinderKotlett = new DummySpeise().beliebtheit(14).mitZutat(DummyZutat.steaks()).name("rinderKotlett").erstelle();
        Dish rinderNackenSteaks = new DummySpeise().beliebtheit(15).mitZutat(DummyZutat.steaks()).name("rinderNackenSteaks").erstelle();
        Dish knoblauchgarnelen = new DummySpeise().beliebtheit(3).mitZutat(DummyZutat.garnelen()).name("Knoblauchgarnelen").erstelle();
        Dish garnelenCocktail = new DummySpeise().beliebtheit(6).mitZutat(DummyZutat.garnelen()).name("garnelenCocktail").erstelle();
        Dish garnelensalat = new DummySpeise().beliebtheit(9).mitZutat(DummyZutat.garnelen()).name("garnelensalat").erstelle();
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
        Dish bratkartoffeln = new DummySpeise().beliebtheit(4).mitZutat(DummyZutat.kartoffeln()).name("Salzkartoffeln").erstelle();
        Dish steak = new DummySpeise().beliebtheit(5).mitZutat(DummyZutat.steaks()).name("Schuhsohlen").erstelle();
        Dish knoblauchgarnelen = new DummySpeise().beliebtheit(6).mitZutat(DummyZutat.garnelen()).name("Garnelendärme").erstelle();
        unfavorites.add(bratkartoffeln);
        unfavorites.add(steak);
        unfavorites.add(knoblauchgarnelen);
        return unfavorites;
    }
}
