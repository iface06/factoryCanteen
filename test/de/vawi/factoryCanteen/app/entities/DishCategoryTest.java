package de.vawi.factoryCanteen.app.entities;

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
    public void testFindVegetarianFromAbbreviation() {
        DishCategory category = DishCategory.byAbbrevation("v");
        assertEquals(DishCategory.VEGETARIAN, category);
    }
    
    @Test(expected = DishCategory.UnknownDishCategory.class)
    public void testUnknownDishCategory(){
        DishCategory category = DishCategory.byAbbrevation("x");
    }
}
