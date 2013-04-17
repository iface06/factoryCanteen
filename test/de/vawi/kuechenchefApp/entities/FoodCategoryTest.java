

package de.vawi.kuechenchefApp.entities;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class FoodCategoryTest {

    @Test
    public void testFindFishByAbbreviation() {
        FoodCategory founded = FoodCategory.byAbbrevation("f");
        assertEquals(FoodCategory.FISH, founded);
    }
    
    @Test
    public void testFindMeatByAbbreviation() {
        FoodCategory founded = FoodCategory.byAbbrevation("m");
        assertEquals(FoodCategory.MEAT, founded);
    }
    
    @Test
    public void testFromAbbreviation() {
        FoodCategory founded = FoodCategory.byAbbrevation("");
        assertEquals(FoodCategory.VEGETARIAN, founded);
    }

    @Test
    public void testGetOtherOwnTo(){
        List<FoodCategory> founded = FoodCategory.getOtherOwnTo(FoodCategory.FISH);
        
        assertTrue(founded.contains(FoodCategory.MEAT));
        assertTrue(founded.contains(FoodCategory.VEGETARIAN));
    }
}