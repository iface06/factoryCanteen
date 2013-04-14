

package de.vawi.kuechenchefApp.menues;

import de.vawi.kuechenchefApp.Periode;
import de.vawi.kuechenchefApp.entities.Dish;
import java.util.List;


public interface CreateMenuDao {
    
    public List<Dish> findeBeliebtesteSpeisenFuerPlanungsPeriode(Periode periode);
    public List<Dish> findeUnbeliebtesteSpeisen(Periode periode);
    public boolean sindAusreichendSpeisenFuerSpeiseplanErstellungVorhanden();

}
