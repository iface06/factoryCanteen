

package de.vawi.kuechenchefApp.entities;

import de.vawi.kuechenchefApp.dummies.DummyZutat;
import org.junit.*;
import static org.junit.Assert.*;


public class IngredientTest {

    @Test
    public void testEquals() {
        Ingredient potato1 = new DummyZutat().name("Potato").erstelle();
        Ingredient potato2 = new DummyZutat().name("Potato").erstelle();
        
        assertTrue(potato1.equals(potato2));
    }
    
    @Test
    public void testNotEquals() {
        Ingredient potato = new DummyZutat().name("Potato").erstelle();
        Ingredient chili = new DummyZutat().name("Chili").erstelle();
        
        assertFalse(potato.equals(chili));
    }

}