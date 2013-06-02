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
    private TestableFileSystem fileSystem;

    @Test
    public void testImportPriceListsBeforeDishes() {
        fileSystem.setUp();
        assertEquals(1, loadDishesWasCalled);
    }

    @Test
    public void testWriteMenusToDiskCalled() {
        fileSystem.tearDown();
        assertEquals(1, writeMenusWasCalled);
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
        protected void writeMenuesToDisk() {
            writeMenusWasCalled++;
        }
    }
}
