package de.vawi.factoryCanteen.persistence;

import de.vawi.factoryCanteen.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.persistence.dishes.DishesImport;
import de.vawi.factoryCanteen.persistence.interactorDaos.FileDaoFactory;
import de.vawi.factoryCanteen.persistence.offers.OffersDB;

/**
 *
 * Mit dieser Klasse wird das Einlesen und Schreiben von Daten in das
 * Dateisystem ausgeführt.
 *
 * @author Tobias
 */
public class FileSystem {

    public void setUp() {
        importDishes();
        importOffers();
        instanciateDao();
    }

    public void tearDown() {
        writeMenuesToDisk();
    }

    protected void importDishes() {
        DishesImport importer = new DishesImport();
        importer.importFiles();
    }

    protected void importOffers() {
        OffersDB.getInstance().deserializeOffers();
    }

    protected void writeMenuesToDisk() {
        OffersDB menues = OffersDB.getInstance();
        menues.serializeOffers();
    }

    protected void instanciateDao() {
        DaoFactory.INSTANCE = new FileDaoFactory();
    }
}
