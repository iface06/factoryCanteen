package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;

/**
 *
 * @author Tobias
 */
class AlternativeDihesRule extends NoReplicationRule {

    private List<Dish> favorDishes;
    private CreateMenuDao dao;
    private int requiredDishesPerDay;

    public AlternativeDihesRule(int requiredDishesPerDay) {
        this.requiredDishesPerDay = requiredDishesPerDay;
    }

    @Override
    protected Dish selectDishForOffer(int dishNumber) {
        loadDishes();
        if (!(dishNumber >= favorDishes.size())) {
            return favorDishes.get(dishNumber);
        } else {
            throw new NotEnoughDishesForMenuCreationAvailable();
        }
    }

    @Override
    protected boolean isOfferRequiredForOfferDate(Date offerDate, List<Offer> offers) {
        return offers.size() < requiredDishesPerDay;
    }

    private void loadDishes() {
        if (favorDishes == null) {
            favorDishes = dao.findFavorDishesForPeriode();
        }
    }

    @Override
    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }
}
