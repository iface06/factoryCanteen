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
class FishOnFridayRule extends NoReplicationRule {

    private CreateMenuDao dao;
    private List<Dish> fishDishes;

    @Override
    protected Dish selectDishForOffer(int dishNumber) {
        loadFishDishes();
        if (!(dishNumber >= fishDishes.size())) {
            return fishDishes.get(dishNumber);
        } else {
            throw new NotEnoughDishesForMenuCreationAvailable();
        }
    }

    @Override
    protected boolean isOfferRequiredForOfferDate(Date offerDate, List<Offer> offers) {
        return new DateTime(offerDate).getDayOfWeek() == DateTimeConstants.FRIDAY;
    }

    private void loadFishDishes() {
        if (fishDishes == null) {
            fishDishes = dao.findDishesByCategory(DishCategory.FISH);
        }
    }

    @Override
    public void setDao(CreateMenuDao offerCreatorDao) {
        this.dao = offerCreatorDao;
    }
}
