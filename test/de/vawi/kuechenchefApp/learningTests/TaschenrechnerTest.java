/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.learningTests;

import de.vawi.kuechenchefApp.learningTests.Taschenrechner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max
 */
public class TaschenrechnerTest {
    
    /**
     * zahl1 + zahl2 = ergebnis -> OK
     * zahl1 - zahl2 = ergebnis -> OK
     * zahl1 * zahl2 = ergebnis
     * zahl1/zahl2 = ergebnis
     * zahl1/0 = 0
     */
    
    @Test
    public void testAddition(){
        Taschenrechner rechner = new Taschenrechner();
        int ergebnis = rechner.addiere(5, 3);
        
        assertEquals(8, ergebnis);
    }
    
    @Test 
    public void testSubtrahiere(){
        Taschenrechner rechner = new Taschenrechner();
        int ergebnis = rechner.subtrahiere(5, 3);
        
        assertEquals(2, ergebnis);
    }
    

   
}
