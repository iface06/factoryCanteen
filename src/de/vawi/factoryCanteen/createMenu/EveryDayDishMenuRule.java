package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;
import org.joda.time.DateTime;

/**
 *
 * @author Tobias
 */
class EveryDayDishMenuRule implements MenuCreationRule {

    private List<Dish> dishesByCategory;
    private CreateMenuDao dao;
    private DishCategory dishCategory;
    private int dishNumber = 0;

    public EveryDayDishMenuRule(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public void execute(List<Offer> offers, Date offerDate) {
        loadDishes();
        Offer offer = createOffer(offerDate);
        offers.add(offer);
    }

    @Override
    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }

    private Offer createOffer(Date day) {
        Offer offer = new Offer();
        offer.setDate(day);
        offer.setDish(dishesByCategory.get(dishNumber));
        dishNumber++;
        return offer;
    }

    public void loadDishes() throws NotEnoughDishesForMenuCreationAvailable {
        if (dishesByCategory == null) {
            dishesByCategory = dao.findDishesByCategory(dishCategory);
        }
    }
}
