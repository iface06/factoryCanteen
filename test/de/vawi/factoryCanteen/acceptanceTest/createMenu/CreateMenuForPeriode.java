package de.vawi.factoryCanteen.acceptanceTest.createMenu;

import de.vawi.factoryCanteen.acceptanceTest.GroupOffersByDate;
import de.vawi.factoryCanteen.entities.PeriodeConfiguration;
import de.vawi.factoryCanteen.entities.Offer;
import de.vawi.factoryCanteen.entities.DishCategory;
import de.vawi.factoryCanteen.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.createMenu.CreateMenusInteractor;
import de.vawi.factoryCanteen.createMenu.CreateMenusRequest;
import de.vawi.factoryCanteen.createMenu.MenuCreator;
import de.vawi.factoryCanteen.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import de.vawi.factoryCanteen.persistence.dishes.DishesImport;
import de.vawi.factoryCanteen.persistence.interactorDaos.CreateMenuesFileDao;
import de.vawi.factoryCanteen.persistence.interactorDaos.FileDaoFactory;
import java.util.*;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.jbehave.core.io.StoryPathResolver;

public class CreateMenuForPeriode {

    private PeriodeConfiguration periode;
    private List<Offer> offers;

    @Given("a menu contains $numberOfMealsPerDay meals per day for the next $numberOfDayPerWeek days")
    public void pressCreateButton(int numberOfMealsPerDay, int numberOfDayPerWeek) {
        periode = new PeriodeConfiguration();
        periode.setNumberOfDaysPerWeek(numberOfDayPerWeek);
        periode.setNumberOfMealsPerDay(numberOfMealsPerDay);
    }

    @When("the periode is $weeks weeks")
    public void offeredThreeMenusForTheNextThreeWeeks(int weeks) {
        periode.setNumberOfWeek(weeks);
        DishesImport importer = new DishesImport();
        importer.importFiles();
        DaoFactory.INSTANCE = new FileDaoFactory();
        CreateMenusInteractor interactor = new CreateMenusInteractor(new RequestBoundary<CreateMenusRequest>() {
            @Override
            public CreateMenusRequest passRequest() {
                CreateMenusRequest request = new CreateMenusRequest();
                request.setPeriode(periode);
                return request;
            }
        });
        interactor.setDao(DaoFactory.INSTANCE.makeCreateMenuDao());
        interactor.setMenuCreator(new MenuCreator());
        interactor.execute();

        offers = interactor.getResponse();

    }

    @Then("expect $days days with $expectedNumberOfMeals meals")
    public void menusContainsEnoughtMealsForThePeriode(int days, int expectedNumberOfMeals) {
        assertThat(offers.size(), equalTo(periode.calculateRequiredMealsForPeriode()));
    }

    @Then("each day with 3 dishes")
    public void requiredNumberOfDishesPerDayAreOffered() {

        Map<Date, List<Offer>> groupedOffersPerDay = groupOffersByDate();
        for (Date date : groupedOffersPerDay.keySet()) {
            List<Offer> offersPerDay = groupedOffersPerDay.get(date);
            assertThat(offersPerDay.size(), equalTo(periode.getNumberOfDishesPerDay()));
        }

    }

    @Then("each day contains a vegetarian dish, a dish with meat and a third alternativ")
    public void menusContainsEnoughtMealsForThePeriode() {
        Map<Date, List<Offer>> groupedOffersPerDay = groupOffersByDate();
        for (Date date : groupedOffersPerDay.keySet()) {
            List<Offer> offersPerDay = groupedOffersPerDay.get(date);
            int numberOfMeatDishes = countDishesByDishCategory(offersPerDay, DishCategory.MEAT);
            int numberOfVegetarian = countDishesByDishCategory(offersPerDay, DishCategory.VEGETARIAN);

            assertThat(offersPerDay.size(), equalTo(periode.getNumberOfDishesPerDay()));
            assertThat(numberOfMeatDishes, greaterThan(0));
            assertThat(numberOfVegetarian, greaterThan(0));
        }
    }

    private Map<Date, List<Offer>> groupOffersByDate() {
        return new GroupOffersByDate().apply(offers);
    }

    public int countDishesByDishCategory(List<Offer> offersAday, DishCategory category) {
        int numberOfMeatDishes = 0;
        for (Offer offer : offersAday) {
            if (offer.getDishCategory().equals(category)) {
                numberOfMeatDishes++;
            }
        }
        return numberOfMeatDishes;
    }
}
