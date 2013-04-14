
package de.vawi.kuechenchefApp;

import org.junit.*;
import static org.junit.Assert.*;

public class PlanungsPeriodeTest {

    @Test
    public void testAnzahlBenoetigerGerichte() {
        Periode periode = new Periode();
        assertEquals(45, periode.berechneAnzahlBenoetigterSpeisen());
    }
    
    @Test
    public void testAnzahlBenoetigteFischGerichte() {
        Periode periode = new Periode();
        assertEquals(3, periode.berechneAnzahlBenoetigterFischSpeisen());
    }
    
    @Test
    public void testAnzahlBenoetigteVegetarischeGerichte() {
        Periode periode = new Periode();
        assertEquals(15, periode.berechneAnzahlBenoetigteVegetarischeSpeisen());
    }
    
    @Test
    public void testAnzahlBenoetigteFleischGerichte() {
        Periode periode = new Periode();
        assertEquals(15, periode.berechneAnzahlBenoetigteFleischSpeisen());
    }
    
}