

package de.vawi.kuechenchefApp.entities;

import org.junit.*;
import static org.junit.Assert.*;


public class FarmerTest {

    @Test
    public void testCalculationDeliveryCosts() {
        Supplier supplier = new Farmer();
        supplier.setLieferKostenFaktor(100);
        
        assertEquals(200.0, supplier.berechneLieferkosten(1000), 0.001);
                
    }

}