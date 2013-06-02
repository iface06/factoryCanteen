package de.vawi.factoryCanteen.entities;

import de.vawi.factoryCanteen.entities.Dish;
import org.junit.*;
import static org.junit.Assert.*;

public class DishTest {

    @Test
    public void testNotEquals() {
        Dish a = new Dish();
        a.setName("Steaks");

        Dish b = new Dish();
        b.setName("Blumenkohl");

        assertFalse(a.equals(b));
    }

    @Test
    public void testEquals() {
        Dish a = new Dish();
        a.setName("Steaks");

        Dish b = new Dish();
        b.setName("Steaks");

        assertTrue(a.equals(b));
    }
}