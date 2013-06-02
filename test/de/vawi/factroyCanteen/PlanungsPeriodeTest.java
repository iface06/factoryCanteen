
package de.vawi.factroyCanteen;

import de.vawi.factoryCanteen.entities.Periode;
import org.junit.*;
import static org.junit.Assert.*;

public class PlanungsPeriodeTest {

    @Test
    public void testAnzahlBenoetigerGerichte() {
        Periode periode = new Periode();
        assertEquals(45, periode.quantityOfRequiredDishes());
    }
    
    @Test
    public void testAnzahlBenoetigteFischGerichte() {
        Periode periode = new Periode();
        assertEquals(3, periode.calculateRequiredFishDishes());
    }
    
    @Test
    public void testAnzahlBenoetigteVegetarischeGerichte() {
        Periode periode = new Periode();
        assertEquals(15, periode.berechneAnzahlBenoetigteVegetarischeSpeisen());
    }
    
    @Test
    public void testAnzahlBenoetigteFleischGerichte() {
        Periode periode = new Periode();
        assertEquals(15, periode.calculateNumberOfRequiredMeatDishes());
    }
    
}