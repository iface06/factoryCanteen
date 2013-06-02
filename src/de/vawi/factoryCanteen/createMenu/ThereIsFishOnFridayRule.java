package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 *
 * @author Tobias
 */
class ThereIsFishOnFridayRule {

    private List<Offer> offers;
    private CreateMenuDao dao;
    private Date startDate;
    private List<Dish> fishDishes;
    private Periode periode;

    void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    void setDao(CreateMenuDao offerCreatorDao) {
        this.dao = offerCreatorDao;
    }

    void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public void execute() {
        fishDishes = dao.findDishesByCategory(DishCategory.FISH);
        for (int weekNumber = 0; weekNumber < periode.getNumberOfWeeks(); weekNumber++) {
            Offer fishDish = new Offer();
            fishDish.setDish(fishDishes.get(weekNumber));
            fishDish.setDate(nextFriday(weekNumber));
            offers.add(fishDish);
        }
    }

    private Date nextFriday(int weekNumber) {
        return new DateTime(startDate).plusWeeks(weekNumber).withDayOfWeek(DateTimeConstants.FRIDAY).toDate();
    }
}
