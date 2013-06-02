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
class ThereIsFishOnFridayRule implements MenuCreationRule {

    private List<Offer> offers;
    private CreateMenuDao dao;
    private Date startDate;
    private List<Dish> fishDishes;
    private PeriodeConfiguration periode;

    @Override
    public void execute(List<Offer> offers) {
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

    @Override
    public void setDao(CreateMenuDao offerCreatorDao) {
        this.dao = offerCreatorDao;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setPeriode(PeriodeConfiguration periode) {
        this.periode = periode;
    }
}
