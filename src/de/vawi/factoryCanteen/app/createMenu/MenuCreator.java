package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 *
 * @author Tobias
 */
public class MenuCreator {

    private List<Offer> offers = new ArrayList<>();
    private PeriodeConfiguration periode;
    private CreateMenuDao dao;
    private Date startDateOfPeriode;
    private List<Date> offerDates = new ArrayList<>();
    private Queue<MenuCreationRule> rules = new LinkedList();

    public List<Offer> create() {
        calculateStartDateForNextPeriode();
        calculateListOfOfferDatesForPeriode();

        for (Date offerDate : offerDates) {
            List<Offer> offersForOfferDate = new ArrayList<>();
            for (MenuCreationRule menuCreationRule : rules) {
                menuCreationRule.setDao(dao);
                menuCreationRule.execute(offersForOfferDate, offerDate);
            }
            offers.addAll(offersForOfferDate);
        }
        sortOffersByDate();
        return offers;
    }

    public void setPeriode(PeriodeConfiguration periode) {
        this.periode = periode;
    }

    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }

    protected void addRule(MenuCreationRule everyDayDishMenuRule) {
        rules.offer(everyDayDishMenuRule);
    }

    private void calculateStartDateForNextPeriode() {
        Date date = dao.findDateOfLastOffer();
        startDateOfPeriode = new DateTime(date).plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY).toDate();
    }

    private void sortOffersByDate() {
        Collections.sort(offers, new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    private void calculateListOfOfferDatesForPeriode() {
        int day = 0;
        while (offerDates.size() < periode.getNumberOfDays()) {
            DateTime nextOfferDate = new DateTime(startDateOfPeriode).plusDays(day).withTime(0, 0, 0, 0);
            if (isAWorkingDay(nextOfferDate)) {
                offerDates.add(nextOfferDate.toDate());
            }
            day++;
        }
    }

    private boolean isAWorkingDay(DateTime nextOfferDate) {
        int weekday = nextOfferDate.getDayOfWeek();
        return weekday != DateTimeConstants.SATURDAY && weekday != DateTimeConstants.SUNDAY;
    }
}
