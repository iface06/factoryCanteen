package de.vawi.factoryCanteen.persistence.dishes;

import de.vawi.factoryCanteen.persistence.dishes.DishesImport;
import de.vawi.factoryCanteen.persistence.dishes.DishesDB;
import de.vawi.factoryCanteen.entities.Dish;
import static org.junit.Assert.*;
import org.junit.*;

public class ImportIntegrationTest {

    @BeforeClass
    public static void beforeClass() {
        importDishes();
    }

    @Test
    public void testImportSpeisenVonDateiHitlisteUndRezepte() {

        assertEquals(109, DishesDB.getInstance().numberOfDishes());
    }

    @Test
    public void testAlleSpeisenHabenEinenNamen() {
        assertTrue(DishesDB.getInstance().numberOfDishes() > 0);
        for (Dish speise : DishesDB.getInstance().findFavorDishes()) {
            assertFalse(speise.getName().isEmpty());
        }
    }

    private static void importDishes() {
        DishesImport importer = new DishesImport();
        importer.importFiles();
    }
}
