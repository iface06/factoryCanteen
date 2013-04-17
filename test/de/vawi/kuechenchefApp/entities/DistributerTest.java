

package de.vawi.kuechenchefApp.entities;

import org.junit.*;
import static org.junit.Assert.*;


public class DistributerTest {

    @Test
    public void testCalculatDeliveryCosts() {
        Supplier supplier = new Distributer();
        supplier.setLieferKostenFaktor(1.5);
        
        assertEquals(500.0, supplier.berechneLieferkosten(1000.0), 0.001);
    }

}