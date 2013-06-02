package de.vawi.factoryCanteen.acceptanceTest.createMenu;

import de.vawi.factoryCanteen.entities.PeriodeConfiguration;
import de.vawi.factoryCanteen.entities.Offer;
import de.vawi.factoryCanteen.entities.DishCategory;
import de.vawi.factoryCanteen.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.createMenu.OffersCreator;
import java.util.*;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.jbehave.core.io.StoryPathResolver;

public class EnoughtMealsPerPeriode {

    private PeriodeConfiguration periode;
    private List<Offer> offers;
    private OffersCreator creator;

    @Given("a menu contains $numberOfMealsPerDay meals per day for the next $numberOfDayPerWeek days")
    public void pressCreateButton(int numberOfMealsPerDay, int numberOfDayPerWeek) {
        periode = new PeriodeConfiguration();
        periode.setNumberOfDaysPerWeek(numberOfDayPerWeek);
        periode.setNumberOfMealsPerDay(numberOfMealsPerDay);
    }

    @When("the periode is $weeks weeks")
    public void offeredThreeMenusForTheNextThreeWeeks(int weeks) {
        periode.setNumberOfWeek(weeks);
        creator = new OffersCreator();
        //creator.setDao(new CreateMenuesFileDao());
        creator.setPeriode(periode);
        offers = creator.create();

    }

    @Then("expect $days days with $expectedNumberOfMeals meals")
    public void menusContainsEnoughtMealsForThePeriode(int days, int expectedNumberOfMeals) {
        assertThat(periode.calculateRequiredMealsForPeriode(), equalTo(offers.size()));
    }

    @Then("each day with 3 dishes")
    public void requiredNumberOfDishesPerDayAreOffered() {
        if (!offers.isEmpty()) {
            int countedDishesPerDay = 1;
            Iterator<Offer> offersIterator = offers.iterator();
            Offer firstOffer = offersIterator.next();
            Date currentDate = firstOffer.getDate();
            while (offersIterator.hasNext()) {
                Offer currentOffer = offersIterator.next();
                if (currentDate.equals(currentOffer.getDate())) {
                    countedDishesPerDay++;
                } else {
                    assertThat(periode.getNumberOfMealsPerDay(), equalTo(countedDishesPerDay));
                    currentDate = currentOffer.getDate();
                    countedDishesPerDay = 1;
                }
            }
        }
    }

    @Then("each day contains a vegetarian dish, a dish with meat and a third alternativ")
    public void menusContainsEnoughtMealsForThePeriodeC() {
        if (!offers.isEmpty()) {
            int countedVegetarianMeal = 0;
            int countedMeatMeals = 0;
            Iterator<Offer> offersIterator = offers.iterator();
            Offer firstOffer = offersIterator.next();
            Date currentDate = firstOffer.getDate();
            if (firstOffer.getDish().getCategory().equals(DishCategory.MEAT)) {
                countedMeatMeals++;
            } else if (firstOffer.getDish().equals(DishCategory.VEGETARIAN) || firstOffer.getDish().equals(DishCategory.FISH)) {
                countedVegetarianMeal++;
            }
            while (offersIterator.hasNext()) {
                Offer currentOffer = offersIterator.next();
                if (currentDate.equals(currentOffer.getDate())) {
                    if (firstOffer.getDish().getCategory().equals(DishCategory.MEAT)) {
                        countedMeatMeals++;
                    } else if (firstOffer.getDish().equals(DishCategory.VEGETARIAN) || firstOffer.getDish().equals(DishCategory.FISH)) {
                        countedVegetarianMeal++;
                    }
                } else {
                    assertThat(countedVegetarianMeal, greaterThanOrEqualTo(1));
                    assertThat(countedMeatMeals, greaterThanOrEqualTo(1));
                    currentDate = currentOffer.getDate();
                    countedMeatMeals = 0;
                    countedVegetarianMeal = 0;
                    if (firstOffer.getDish().getCategory().equals(DishCategory.MEAT)) {
                        countedMeatMeals++;
                    } else if (firstOffer.getDish().equals(DishCategory.VEGETARIAN) || firstOffer.getDish().equals(DishCategory.FISH)) {
                        countedVegetarianMeal++;
                    }
                }
            }
        }

    }
}
