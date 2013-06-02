package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.*;

public interface CreateMenuDao {

    public List<Dish> findFavorDishesForPeriode();

    public void storeOffers(List<Offer> offers);

    public List<Dish> findDishesByCategory(DishCategory category);

    public Date findDateOfLastOffer();
}
