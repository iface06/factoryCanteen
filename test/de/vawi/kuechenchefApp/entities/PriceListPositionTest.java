

package de.vawi.kuechenchefApp.entities;

import org.junit.*;
import static org.junit.Assert.*;


public class PriceListPositionTest {

    @Test
    public void testCalculatePricePerUnit() {
        PriceListPosition postion = new PriceListPosition();
        postion.setGebindeGroesse(5.0);
        postion.setPreis(100.0);
        
        assertEquals(20.0, postion.berechnePreisProEinheit(), 0.001);
    }
    
    @Test
    public void testCalculateNumberOfPackagesForAmount() {
        PriceListPosition postion = new PriceListPosition();
        postion.setGebindeGroesse(5.0);
        postion.setPreis(100.0);
        
        assertEquals(4.0, postion.berechneAnzahlGebindeFuerMenge(20.0), 0.001);
    }
    
    @Test
    public void testCalculatePriceForAmount() {
        PriceListPosition postion = new PriceListPosition();
        postion.setGebindeGroesse(5.0);
        postion.setPreis(100.0);
        
        assertEquals(400.0, postion.berechnePreisFuerMenge(20.0), 0.001);
    }

}