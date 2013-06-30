package de.vawi.factoryCanteen.persistence.dishes;

import de.vawi.factoryCanteen.persistence.dishes.DishCreator;
import de.vawi.factoryCanteen.app.entities.Dish;
import de.vawi.factoryCanteen.app.entities.DishCategory;
import org.junit.*;
import static org.junit.Assert.*;

public class DishCreatorTest {

    @Test
    public void testSpeisenErstellungAusHitlisteZeile() {
        String hitlisteZeile = "1,\"Bohneneintopf Mexiko\",\"m\"";
        Dish speise = new DishCreator().create(hitlisteZeile);

        assertEquals(new Integer(1), speise.getPopularity());
        assertEquals("Bohneneintopf Mexiko", speise.getName());
        assertEquals(DishCategory.MEAT, speise.getCategory());
    }
}
