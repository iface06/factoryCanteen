/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.persistence.offers;

import de.vawi.factoryCanteen.entities.Dish;
import de.vawi.factoryCanteen.entities.DishCategory;
import de.vawi.factoryCanteen.entities.Offer;
import java.io.*;
import java.util.*;
import org.joda.time.DateTime;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Tobias
 */
public class OffersDBIntegrationTest {

    private OffersDB db;
    private static List<Offer> offersSerialized;

    @BeforeClass
    public static void beforeClass() throws IOException {
        createOfferForSerialitationAndDeserialitation();
        File offers = new File("importFiles/offers.ser");
        if (offers.isFile()) {
            offers.renameTo(new File("importFiles/offers_backup.ser"));
        } else {
            offers.createNewFile();
        }
    }

    @Test
    public void testWriteOffersToFileSystem() {
        db = OffersDB.getInstance();
        db.storeOffers(offersSerialized);
        db.serializeOffers();
    }

    @Test
    public void testReadOffersFormFileSystem() {
        db = OffersDB.getInstance();
        db.deserializeOffers();
        assertEquals(offersSerialized.size(), db.offers.size());
    }

    @AfterClass
    public static void afterClass() {
        File offers = new File("importFiles/offers.ser");
        offers.delete();
        File backup = new File("importFiles/offers_backup.ser");
        backup.renameTo(new File("importFiles/offers.ser"));
    }

    private static Offer createOffer() {
        Offer offer = new Offer();
        offer.setDate(new DateTime().withTime(0, 0, 0, 0).toDate());
        Dish d = new Dish();
        d.setCategory(DishCategory.MEAT);
        d.setName("Argentinisches Rostbeef italenischer Art");
        d.setPopularity(1);
        offer.setDish(d);
        return offer;
    }

    private static void createOfferForSerialitationAndDeserialitation() {
        offersSerialized = new ArrayList<>();
        Offer offer = createOffer();
        offersSerialized.add(offer);
    }
}
