package de.vawi.factoryCanteen.persistence.learningTests;

import de.vawi.factoryCanteen.entities.Dish;
import de.vawi.factoryCanteen.entities.DishCategory;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ObjectSerializerTest {

    public static final String DISHES_FILE_NAME = "test/de/vawi/factoryCanteen/learningTests/dishes.ser";
    public static final String DISH_FILE_NAME = "test/de/vawi/factoryCanteen/learningTests/dish.ser";
    private Dish dish;
    private FileOutputStream outputFile;
    private ObjectOutputStream objectWriter;
    private FileInputStream inputFile;
    private ObjectInputStream objectReader;

    @Test
    public void testWriteObjectToFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        createObjectWriterForFile(DISH_FILE_NAME);
        objectWriter.writeObject(dish);
        closeOutputFile();
    }

    @Test
    public void testReadObjectToFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        createObjectReader(DISH_FILE_NAME);

        Dish deserializedDish = (Dish) objectReader.readObject();
        closeInputFile();

        assertEquals(dish.getPopularity(), deserializedDish.getPopularity());
        assertEquals(dish.getName(), deserializedDish.getName());
        assertEquals(dish.getCategory(), deserializedDish.getCategory());
    }

    @Test
    public void testWriteDishesToFile() throws FileNotFoundException, IOException {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish);
        dishes.add(dish);
        createObjectWriterForFile(DISHES_FILE_NAME);

        objectWriter.writeObject(dishes);
        closeOutputFile();
    }

    @Test
    public void testReadDishesToFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        createObjectReader(DISHES_FILE_NAME);
        List<Dish> deserializedDish = (List<Dish>) objectReader.readObject();
        closeInputFile();

        assertEquals(2, deserializedDish.size());
        assertEquals(dish.getName(), deserializedDish.get(0).getName());
        assertEquals(dish.getPopularity(), deserializedDish.get(0).getPopularity());
    }

    @Before
    public void before() throws IOException, FileNotFoundException {
        dish = new Dish();
        dish.setPopularity(1);
        dish.setName("Schnipo");
        dish.setCategory(DishCategory.MEAT);
    }

    private void closeOutputFile() throws IOException {
        outputFile.close();
    }

    private void closeInputFile() throws IOException {
        inputFile.close();
    }

    private void createObjectWriterForFile(String fileName) throws IOException, FileNotFoundException {
        outputFile = new FileOutputStream(fileName);
        objectWriter = new ObjectOutputStream(outputFile);
    }

    private void createObjectReader(String fileName) throws FileNotFoundException, IOException {
        inputFile = new FileInputStream(fileName);
        objectReader = new ObjectInputStream(inputFile);
    }
}
