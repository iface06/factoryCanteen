

package de.vawi.kuechenchefApp.createMenu;

import de.vawi.kuechenchefApp.entities.Menu;
import de.vawi.kuechenchefApp.entities.Periode;
import de.vawi.kuechenchefApp.entities.Dish;
import java.util.List;


public interface CreateMenuDao {
    
    public List<Dish> findeBeliebtesteSpeisenFuerPlanungsPeriode(Periode periode);
    public List<Dish> findeUnbeliebtesteSpeisen(Periode periode);
    public boolean areEnoughtDishesAvailable();
    public void storeMenues(List<Menu> menues);

}
