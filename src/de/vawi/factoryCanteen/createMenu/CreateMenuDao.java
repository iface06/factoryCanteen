

package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.Menu;
import de.vawi.factoryCanteen.entities.Periode;
import de.vawi.factoryCanteen.entities.Dish;
import java.util.List;


public interface CreateMenuDao {
    
    public List<Dish> findFavoriteDishesForPeriode(Periode periode);
    public List<Dish> findeUnbeliebtesteSpeisen(Periode periode);
    public boolean areEnoughtDishesAvailable();
    public void storeMenues(List<Menu> menues);

}
