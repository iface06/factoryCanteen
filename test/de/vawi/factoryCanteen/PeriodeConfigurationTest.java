
package de.vawi.factoryCanteen;

import de.vawi.factoryCanteen.app.entities.PeriodeConfiguration;
import org.junit.*;
import static org.junit.Assert.*;

public class PeriodeConfigurationTest {

    @Test
    public void testAnzahlBenoetigerGerichte() {
        PeriodeConfiguration periode = new PeriodeConfiguration();
        assertEquals(45, periode.quantityOfRequiredDishes());
    }
    
    @Test
    public void testAnzahlBenoetigteFischGerichte() {
        PeriodeConfiguration periode = new PeriodeConfiguration();
        assertEquals(3, periode.calculateRequiredFishDishes());
    }
    
    @Test
    public void testAnzahlBenoetigteVegetarischeGerichte() {
        PeriodeConfiguration periode = new PeriodeConfiguration();
        assertEquals(15, periode.berechneAnzahlBenoetigteVegetarischeSpeisen());
    }
    
    @Test
    public void testAnzahlBenoetigteFleischGerichte() {
        PeriodeConfiguration periode = new PeriodeConfiguration();
        assertEquals(15, periode.calculateNumberOfRequiredMeatDishes());
    }
    
}