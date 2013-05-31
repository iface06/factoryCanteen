package de.vawi.kuechenchefApp.acceptanceTest.createMenu;

import de.vawi.kuechenchefApp.createMenu.OffersCreator;
import de.vawi.kuechenchefApp.entities.*;
import java.util.List;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.jbehave.core.io.StoryPathResolver;

public class EnoughtMealsPerPeriode {

    private Periode periode;
    private List<Offer> menu;
    private OffersCreator creator;

    @Given("a menu contains $numberOfMealsPerDay meals per day for the next $numberOfDayPerWeek days")
    public void pressCreateButton(int numberOfMealsPerDay, int numberOfDayPerWeek) {
        periode = new Periode();
        periode.setNumberOfDaysPerWeek(numberOfDayPerWeek);
        periode.setNumberOfMealsPerDay(numberOfMealsPerDay);
    }

    @When("the periode is $weeks weeks")
    public void offeredThreeMenusForTheNextThreeWeeks(int weeks) {
        periode.setNumberOfWeek(weeks);
        creator = new OffersCreator();
        menu = creator.create();

    }

    @Then("expect $days days with $expectedNumberOfMeals meals")
    public void menusContainsEnoughtMealsForThePeriode(int days, int expectedNumberOfMeals) {
    }

    @Then("each day with 3 dishes")
    public void menusContainsEnoughtMealsForThePeriodeA() {

        assertThat(3, equalTo(3));
    }

    @Then("each day contains a vegetarian dish, a dish with meat and a third alternativ")
    public void menusContainsEnoughtMealsForThePeriodeC() {

        assertThat(3, equalTo(2));
    }
}
