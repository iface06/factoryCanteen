package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;
import org.joda.time.DateTime;

/**
 *
 * @author Tobias
 */
class EveryDayDishMenuRule extends NoReplicationRule {

    private List<Dish> dishesByCategory;
    private CreateMenuDao dao;
    private DishCategory dishCategory;

    public EveryDayDishMenuRule(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    @Override
    protected Dish selectDishForOffer(int dishNumber) {
        loadDishes();
        if (!(dishNumber >= dishesByCategory.size())) {
            return dishesByCategory.get(dishNumber);
        } else {
            throw new NotEnoughDishesForMenuCreationAvailable();
        }
    }

    @Override
    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }

    public void loadDishes() throws NotEnoughDishesForMenuCreationAvailable {
        if (dishesByCategory == null) {
            dishesByCategory = dao.findDishesByCategory(dishCategory);
        }
    }
}
