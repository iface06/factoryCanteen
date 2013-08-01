package de.vawi.factoryCanteen.persistence.dishes;

import de.vawi.factoryCanteen.persistence.dishes.DishesDB;
import de.vawi.factoryCanteen.app.entities.Dish;
import de.vawi.factoryCanteen.app.entities.DishCategory;
import de.vawi.factoryCanteen.persistence.dishes.DishesImport;

import de.vawi.factoryCanteen.persistence.files.CsvFile;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class DishesImportTest {

    private DishesImport importer;
    private DishesDB dishDb;
    private List<String> hitlisteZeilen;

    private void initHitlisteZeilen() {
        hitlisteZeilen = new ArrayList<>();
        hitlisteZeilen.add("1,\"Bohneneintopf Mexiko\",\"v\"");
        hitlisteZeilen.add("2,\"Falscher Hase\",\"m\"");

    }

    @Before
    public void before() {
        initHitlisteZeilen();
        initializiereImporter();
        initizialisiereSpeisenVerwaltung();
    }

    @Test
    public void testSpeisenImportAusHitliste() {
        importer.importFiles();

        assertEquals(new Integer(1), dishDb.findDishByName("Bohneneintopf Mexiko").getPopularity());
        assertEquals(new Integer(2), dishDb.findDishByName("Falscher Hase").getPopularity());
    }

    @Test
    public void testKategorisierungDerVegetarischenSpeisen() {
        importer.importFiles();

        Dish speise = dishDb.findDishByName("Bohneneintopf Mexiko");
        assertEquals(DishCategory.VEGETARIAN, speise.getCategory());
    }

    @Test
    public void testKategorisierungDerFleischSpeisen() {
        importer.importFiles();

        Dish dish = dishDb.findDishByName("Falscher Hase");
        assertEquals(DishCategory.MEAT, dish.getCategory());
    }

    @Test(expected = DishesImport.HitlistFileNotValide.class)
    public void testFehlerhafteHitliste() {
        hitlisteZeilen.add(",\"Falscher Hase\"");
        importer.importFiles();
        fail();
    }

    private void initializiereImporter() {
        importer = new TestbarerImport();
    }

    private void initizialisiereSpeisenVerwaltung() {
        dishDb = DishesDB.getInstance();
    }

    private class TestbarerImport extends DishesImport {

        @Override
        protected CsvFile leseDatei(String dateiPfad) {
            return new TestbareHitlisteDatei();
        }
    }

    private class TestbareHitlisteDatei implements CsvFile {

        @Override
        public String getDateiname() {
            return "TestbareHitliste.csv";
        }

        @Override
        public Iterator<String> iterator() {
            return hitlisteZeilen.iterator();
        }
    }
}
