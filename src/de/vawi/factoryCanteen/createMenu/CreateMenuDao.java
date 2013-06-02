package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.*;
import java.util.List;

public interface CreateMenuDao {

    public List<Dish> findFavorDishesForPeriode(Periode periode);

    public List<Dish> findeUnbeliebtesteSpeisen(Periode periode);

    public boolean areEnoughtDishesAvailable();

    public void storeMenues(List<Menu> menues);

    public List<Dish> findDishesByCategory(DishCategory category);
}
