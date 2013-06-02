package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 *
 * @author Tobias
 */
public class OffersCreator {

    private List<Offer> offers = new ArrayList<>();
    private PeriodeConfiguration periode;
    private CreateMenuDao dao;
    private Date startDateOfPeriode;
    private Queue<MenuCreationRule> rules = new LinkedList();

    public List<Offer> create() {
        calculateStartDateForNextPeriode();
        while (!rules.isEmpty()) {
            MenuCreationRule rule = rules.poll();
            rule.setDao(dao);
            rule.setPeriode(periode);
            rule.setStartDate(startDateOfPeriode);
        }

        return offers;
    }

    private void sortOffersByDate() {
        Collections.sort(offers, new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    public void setPeriode(PeriodeConfiguration periode) {
        this.periode = periode;
    }

    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }

    void addRule(MenuCreationRule everyDayDishMenuRule) {
        rules.offer(everyDayDishMenuRule);
    }

    private void calculateStartDateForNextPeriode() {
        Date date = dao.findDateOfLastOffer();
        startDateOfPeriode = new DateTime(date).plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY).toDate();
    }
}
