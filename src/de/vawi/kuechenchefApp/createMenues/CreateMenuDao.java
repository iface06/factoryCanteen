

package de.vawi.kuechenchefApp.createMenues;

import de.vawi.kuechenchefApp.entities.Menu;
import de.vawi.kuechenchefApp.Periode;
import de.vawi.kuechenchefApp.entities.Dish;
import java.util.List;


public interface CreateMenuDao {
    
    public List<Dish> findeBeliebtesteSpeisenFuerPlanungsPeriode(Periode periode);
    public List<Dish> findeUnbeliebtesteSpeisen(Periode periode);
    public boolean sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden();
    public void storeMenues(List<Menu> menues);

}
