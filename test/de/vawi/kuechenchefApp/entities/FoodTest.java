

package de.vawi.kuechenchefApp.entities;

import org.junit.*;
import static org.junit.Assert.*;


public class FoodTest {

    @Test
    public void testEquals() {
        Food potato1 = createFoodWithName("Potato");
        Food potato2 = createFoodWithName("Potato");
        
        assertTrue(potato1.equals(potato2));
    }
    
    @Test
    public void testNotEquals() {
        Food potato = createFoodWithName("Potato");
        Food chili = createFoodWithName("Chili");
        
        assertFalse(potato.equals(chili));
    }
    
    private Food createFoodWithName(String name) {
        Food potato = new Food();
        potato.setName(name);
        return potato;
    }

}