package de.vawi.factoryCanteen.persistence.interactorDaos;

import de.vawi.factoryCanteen.app.entities.DishCategory;
import de.vawi.factoryCanteen.app.entities.Offer;
import de.vawi.factoryCanteen.app.entities.Dish;
import de.vawi.factoryCanteen.app.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.persistence.dishes.DishesDB;
import de.vawi.factoryCanteen.persistence.offers.OffersDB;
import java.util.*;

public class CreateMenuesFileDao implements CreateMenuDao {

    private DishesDB dishes = DishesDB.getInstance();
    private OffersDB menues = OffersDB.getInstance();

    @Override
    public List<Dish> findFavorDishesForPeriode() {
        return dishes.findFavorDishes();
    }

    @Override
    public void storeOffers(List<Offer> menues) {
        this.menues.storeOffers(menues);
    }

    @Override
    public List<Dish> findDishesByCategory(DishCategory category) {
        return dishes.findDishesByCategory(category);
    }

    @Override
    public Date findDateOfLastOffer() {
        return menues.findDateOfLastOffer();
    }
}
