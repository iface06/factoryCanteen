package de.vawi.factoryCanteen.acceptanceTest.createMenu;

import de.vawi.factoryCanteen.acceptanceTest.GroupOffersByDate;
import de.vawi.factoryCanteen.app.entities.PeriodeConfiguration;
import de.vawi.factoryCanteen.app.entities.Offer;
import de.vawi.factoryCanteen.app.entities.DishCategory;
import de.vawi.factoryCanteen.app.createMenu.*;
import de.vawi.factoryCanteen.app.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.app.entities.*;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import de.vawi.factoryCanteen.persistence.dishes.DishesImport;
import de.vawi.factoryCanteen.persistence.interactorDaos.FileDaoFactory;
import java.util.*;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateMenuForPeriodeTest {

    private PeriodeConfiguration periode;
    private List<Offer> offers;

    @Given("a menu contains $numberOfMealsPerDay meals per day for the next $numberOfDayPerWeek days")
    public void pressCreateButton(int numberOfMealsPerDay, int numberOfDayPerWeek) {
        periode = new PeriodeConfiguration();
        periode.setNumberOfDaysPerWeek(numberOfDayPerWeek);
        periode.setNumberOfMealsPerDay(numberOfMealsPerDay);
        DishesImport importer = new DishesImport();
        importer.importFiles();
    }

    @When("the periode is $weeks weeks")
    public void offerThreeMenusForTheNextThreeWeeks(int weeks) {
        periode.setNumberOfWeek(weeks);
        DaoFactory.INSTANCE = new FileDaoFactory();
        CreateMenusInteractor interactor = new CreateMenusInteractor(createRequestBoundary());
        interactor.setDao(DaoFactory.INSTANCE.makeCreateMenuDao());
        interactor.setMenuCreator(new MenuCreator());
        interactor.execute();

        offers = interactor.getResponse();

    }

    @Then("expect $days days with $expectedNumberOfMeals dishes")
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

    @Then("each dish is not offered repeatedly in the periode")
    public void eachDishIsNonRecurring() {
        Set<Dish> dishes = new HashSet<>();
        Boolean nonRecurringDish = true;
        for (Offer offer : offers) {
            if (!dishes.add(offer.getDish())) {
                nonRecurringDish = false;
            }
        }

        assertThat("Dishes repeated", nonRecurringDish, is(Boolean.TRUE));
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

    private RequestBoundary<CreateMenusRequest> createRequestBoundary() {
        return new RequestBoundary<CreateMenusRequest>() {
            @Override
            public CreateMenusRequest passRequest() {
                CreateMenusRequest request = new CreateMenusRequest();
                request.setPeriode(periode);
                return request;
            }
        };
    }
}
