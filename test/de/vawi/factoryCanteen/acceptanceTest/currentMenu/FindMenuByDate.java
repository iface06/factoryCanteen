package de.vawi.factoryCanteen.acceptanceTest.currentMenu;

import de.vawi.factoryCanteen.acceptanceTest.GroupOffersByDate;
import de.vawi.factoryCanteen.currentMenu.FindMenuInteractor;
import de.vawi.factoryCanteen.currentMenu.FindMenuRequest;
import de.vawi.factoryCanteen.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.entities.*;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import de.vawi.factoryCanteen.persistence.FileSystem;
import de.vawi.factoryCanteen.persistence.interactorDaos.FileDaoFactory;
import java.util.*;
import org.jbehave.core.annotations.*;
import org.joda.time.DateTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author Tobias
 */
public class FindMenuByDate {

    private RequestBoundary<FindMenuRequest> request;
    private FindMenuInteractor interactor;

    @Given("request to find the menu for the day $date")
    public void requestForMenuForTheWholeWeekOfGivenDay(String date) {
        initPersistence();
        createRequest(date);

    }

    @When("the week contains all working days from monday to friday")
    public void findTheMenu() {
        interactor = new FindMenuInteractor(request);
        interactor.setDao(DaoFactory.INSTANCE.makeFindMenuDao());
        interactor.execute();
    }

    @Then("expect $numberOfOffers offers ")
    public void expectNumberOfOffersPerWeek(int numberOfOffers) {
        List<Offer> offers = interactor.getResponse().getOffers();
        assertThat(offers.size(), equalTo(numberOfOffers));
    }

    @Then("expect each working day their are $numberOfOffersPerDay offers")
    public void expectThatContainsOffersForEachDay(int numberOfOffersPerDay) {
        List<Offer> offers = interactor.getResponse().getOffers();
        Map<Date, List<Offer>> groupedOffers = groupOffersByDate(offers);

        for (Date date : groupedOffers.keySet()) {
            assertThat(groupedOffers.get(date).size(), equalTo(numberOfOffersPerDay));
        }
    }

    private Map<Date, List<Offer>> groupOffersByDate(List<Offer> offers) {
        return new GroupOffersByDate().apply(offers);
    }

    public void createRequest(String date) {
        final DateTime day = DateTime.parse(date);
        request = new RequestBoundary<FindMenuRequest>() {
            @Override
            public FindMenuRequest passRequest() {
                FindMenuRequest request = new FindMenuRequest();
                request.setCalendarWeek(CalendarWeek.fromDate(day.toDate()));
                return request;
            }
        };
    }

    private void initPersistence() {
        FileSystem fileSystem = new FileSystem();
        fileSystem.setUp();
        DaoFactory.INSTANCE = new FileDaoFactory();
    }
}
