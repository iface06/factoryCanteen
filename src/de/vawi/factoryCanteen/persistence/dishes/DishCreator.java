package de.vawi.factoryCanteen.persistence.dishes;

import de.vawi.factoryCanteen.persistence.files.CsvLineSeperator;
import de.vawi.factoryCanteen.app.entities.Dish;
import de.vawi.factoryCanteen.app.entities.DishCategory;
import java.util.*;

/**
 * Diese Klasse fügt zu den Speise-Objekten die Beliebtheit der Speise hinzu.
 *
 * @author Tatsch
 * @version 28.01.2013
 */
class DishCreator {

    public static final int POPLARITY = 0;
    public static final int NAME = 1;
    public static final int DISH_CATEGORY = 2;

    /**
     * Diese Methode übergibt die Abschnitte einer Zeilte der Hitliste an eine
     * Ersteller-Methode, die aus diesen eine Speise erstellt.
     *
     * @param hitlisteZeile eine Zeile der Hitliste.
     * @return Gibt eine Speise wider, die einen Namen und eine Beliebtheit hat.
     */
    Dish create(String hitlisteZeile) {
        List<String> cells = seperatLine(hitlisteZeile);
        Dish dish = createDish(cells);

        return dish;
    }

    /**
     * Diese Methode unterteilt eine Zeile der Hitliste in ihre durch Kommata
     * getrennten Abschnitte (hier: cells).
     *
     * @param hitlisteZeile eine Zeile aus der Datei hitliste.csv.
     * @return Gibt die Abschnitte (cells) der Zeile aus.
     */
    private List<String> seperatLine(String hitlisteZeile) {
        CsvLineSeperator separator = new CsvLineSeperator();
        List<String> cells = separator.separiere(hitlisteZeile);
        return cells;
    }

    /**
     * Diese Methode stellt aus den in der Hitliste gegebenen Informationen
     * (Belibtheit der Speise und Name der Speise) ein Objekt Speise.
     *
     * @param cells die durch Kommata getrennten Abschnitte einer Zeile der
     * Datei hitliste.csv
     * @return Gibt eine Speise samt Name und Beliebtheit wider.
     * @throws NumberFormatException Wirft diese Exception wenn die Zahl, die
     * die Beliebthit angibt, nicht gelesen weren kann.
     */
    private Dish createDish(List<String> cells) throws NumberFormatException {
        Dish d = new Dish();
        d.setPopularity(Integer.valueOf(cells.get(POPLARITY)));
        d.setName(cells.get(NAME));
        d.setCategory(DishCategory.byAbbrevation(cells.get(DISH_CATEGORY)));
        return d;
    }
}
