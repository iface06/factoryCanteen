
package de.vawi.kuechenchefApp;

import org.junit.*;
import static org.junit.Assert.*;

public class PlanungsPeriodeTest {

    @Test
    public void testAnzahlBenoetigerGerichte() {
        PlanungsPeriode periode = new PlanungsPeriode();
        assertEquals(45, periode.berechneAnzahlBenoetigterSpeisen());
    }
    
    @Test
    public void testAnzahlBenoetigteFischGerichte() {
        PlanungsPeriode periode = new PlanungsPeriode();
        assertEquals(3, periode.berechneAnzahlBenoetigterFischSpeisen());
    }
    
    @Test
    public void testAnzahlBenoetigteVegetarischeGerichte() {
        PlanungsPeriode periode = new PlanungsPeriode();
        assertEquals(15, periode.berechneAnzahlBenoetigteVegetarischeSpeisen());
    }
    
    @Test
    public void testAnzahlBenoetigteFleischGerichte() {
        PlanungsPeriode periode = new PlanungsPeriode();
        assertEquals(15, periode.berechneAnzahlBenoetigteFleischSpeisen());
    }
    
}