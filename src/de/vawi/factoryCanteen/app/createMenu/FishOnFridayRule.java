package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 *
 * @author Tobias
 */
class FishOnFridayRule implements MenuCreationRule {

    private CreateMenuDao dao;
    private List<Dish> fishDishes;
    private int dishNumber = 0;

    @Override
    public void execute(List<Offer> offers, Date offerDate) {
        fishDishes = dao.findDishesByCategory(DishCategory.FISH);
        if (isOfferDateAFriday(offerDate)) {
            Offer fishDish = new Offer();
            fishDish.setDish(fishDishes.get(dishNumber));
            fishDish.setDate(offerDate);
            offers.add(fishDish);
            dishNumber++;
        }

    }

    @Override
    public void setDao(CreateMenuDao offerCreatorDao) {
        this.dao = offerCreatorDao;
    }

    private boolean isOfferDateAFriday(Date offerDate) {
        return new DateTime(offerDate).getDayOfWeek() == DateTimeConstants.FRIDAY;
    }
}
