package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.*;
import java.util.*;

public interface CreateMenuDao {

    public List<Dish> findFavorDishesForPeriode();

    public void storeOffers(List<Offer> offers);

    public List<Dish> findDishesByCategory(DishCategory category);

    public Date findDateOfLastOffer();
}
