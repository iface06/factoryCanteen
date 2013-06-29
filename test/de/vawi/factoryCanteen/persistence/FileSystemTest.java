package de.vawi.factoryCanteen.persistence;

import de.vawi.factoryCanteen.persistence.FileSystem;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Komponenten Test
 *
 * Test prüft ob FileSystem beim SetUp erst den PriceListImport und anschließend
 * den DishesImport ausführt.
 *
 * @author Tobias
 */
public class FileSystemTest {

    private int writeMenusWasCalled;
    private int loadDishesWasCalled;
    private int loadOffersWasCalled;
    private int instanciateDaoWasCalled;
    private TestableFileSystem fileSystem;

    @Test
    public void testImportDishes() {
        fileSystem.setUp();
        assertEquals(1, loadDishesWasCalled);
    }

    @Test
    public void testWriteMenusToDiskCalled() {
        fileSystem.tearDown();
        assertEquals(1, writeMenusWasCalled);
    }

    @Test
    public void testImportOffers() {
        fileSystem.setUp();
        assertEquals(1, loadOffersWasCalled);
    }

    @Test
    public void testInstanciateDaoFactory() {
        fileSystem.setUp();
        assertEquals(1, instanciateDaoWasCalled);
    }

    @Before
    public void before() {
        fileSystem = new TestableFileSystem();
    }

    class TestableFileSystem extends FileSystem {

        @Override
        protected void importDishes() {
            loadDishesWasCalled++;
        }

        @Override
        protected void importOffers() {
            loadOffersWasCalled++;
        }

        @Override
        protected void writeMenuesToDisk() {
            writeMenusWasCalled++;
        }

        @Override
        protected void instanciateDao() {
            instanciateDaoWasCalled++;
        }
    }
}
