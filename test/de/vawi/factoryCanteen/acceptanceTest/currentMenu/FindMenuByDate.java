package de.vawi.factoryCanteen.acceptanceTest.currentMenu;

import de.vawi.factoryCanteen.acceptanceTest.GroupOffersByDate;
import de.vawi.factoryCanteen.app.findMenu.FindMenuInteractor;
import de.vawi.factoryCanteen.app.findMenu.FindMenuRequest;
import de.vawi.factoryCanteen.app.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.app.entities.*;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import de.vawi.factoryCanteen.persistence.FileSystem;
import de.vawi.factoryCanteen.persistence.interactorDaos.FileDaoFactory;
import java.io.*;
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

    private final String PRODUCTIVE_DATA_FILE = "offer.ser";
    private final String PRODUCTIVE_DATA_BACKUP_FILE = "offer_backup.ser";
    private final String TEST_DATA_FILE = "offer_test.ser";
    private RequestBoundary<FindMenuRequest> request;
    private FindMenuInteractor interactor;

    @BeforeScenario
    public void setUpTestDb() throws IOException {
        initPersistence();
    }

    @AfterScenario
    public void tearDownTestDb() {
        restoreProductiveData();
    }

    @Given("request to find the menu for the day $date")
    public void requestForMenuForTheWholeWeekOfGivenDay(String date) {
        createRequest(date);

    }

    @When("the week contains all working days from monday to friday")
    public void findTheMenu() {
        interactor = new FindMenuInteractor(request);
        interactor.setDao(DaoFactory.INSTANCE.makeFindMenuDao());
        interactor.execute();
    }

    @Then("expect $numberOfOffers offers")
    public void expectNumberOfOffersPerWeek(int numberOfOffers) {
        List<Offer> offers = interactor.getResponse();
        assertThat(offers.size(), equalTo(numberOfOffers));
    }

    @Then("expect $numberOfOffersPerDay offers per day")
    public void expectThatContainsOffersForEachDay(String numberOfOffersPerDay) {
        List<Offer> offers = interactor.getResponse();
        Map<Date, List<Offer>> groupedOffers = groupOffersByDate(offers);

        for (Date date : groupedOffers.keySet()) {
            assertThat(groupedOffers.get(date).size(), equalTo(Integer.valueOf(numberOfOffersPerDay)));
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

    private void initPersistence() throws IOException {
        copyProductiveData();
        replaceProductiveDataThroughTestData();
        initFileSystemForTest();
        DaoFactory.INSTANCE = new FileDaoFactory();
    }

    private void copyProductiveData() throws IOException {
        File offers = new File("importFiles/offers.ser");
        if (offers.isFile()) {
            offers.renameTo(new File("importFiles/offers_backup.ser"));
        } else {
            offers.createNewFile();
        }
    }

    private void replaceProductiveDataThroughTestData() {
        replaceFiles(PRODUCTIVE_DATA_FILE, TEST_DATA_FILE);
    }

    private void restoreProductiveData() {
        replaceFiles(PRODUCTIVE_DATA_FILE, PRODUCTIVE_DATA_BACKUP_FILE);
    }

    private void initFileSystemForTest() {
        FileSystem fileSystem = new FileSystem();
        fileSystem.setUp();
    }

    private void replaceFiles(String sourceFileName, String throughFileWithName) {
        File offers = new File("importFiles/" + sourceFileName);
        offers.delete();
        File backup = new File("importFiles/" + throughFileWithName);
        backup.renameTo(new File("importFiles/" + sourceFileName));
    }
}
