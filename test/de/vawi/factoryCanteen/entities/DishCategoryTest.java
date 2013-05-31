package de.vawi.factoryCanteen.entities;

import de.vawi.factoryCanteen.entities.DishCategory;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class DishCategoryTest {

    @Test
    public void testFindFishByAbbreviation() {
        DishCategory founded = DishCategory.byAbbrevation("f");
        assertEquals(DishCategory.FISH, founded);
    }

    @Test
    public void testFindMeatByAbbreviation() {
        DishCategory founded = DishCategory.byAbbrevation("m");
        assertEquals(DishCategory.MEAT, founded);
    }

    @Test
    public void testFromAbbreviation() {
        DishCategory founded = DishCategory.byAbbrevation("v");
        assertEquals(DishCategory.VEGETARIAN, founded);
    }

    @Test
    public void testGetOtherOwnTo() {
        List<DishCategory> founded = DishCategory.getOtherOwnTo(DishCategory.FISH);

        assertTrue(founded.contains(DishCategory.MEAT));
        assertTrue(founded.contains(DishCategory.VEGETARIAN));
    }
}
