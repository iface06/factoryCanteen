package de.vawi.factoryCanteen.persistence.dishes;


import de.vawi.factoryCanteen.app.entities.PeriodeConfiguration;
import de.vawi.factoryCanteen.app.entities.Dish;
import de.vawi.factoryCanteen.app.entities.DishCategory;
import java.util.*;

/**
 * Enthält alle Funktionen die für die Verwaltung der Speisen benötigt.
 *
 * @author Tatsch
 * @version 29.01.2013
 */
public class DishesDB {

    static DishesDB INSTANZ;
    private Set<Dish> dishes = new HashSet<>();
    private PeriodeConfiguration planungsperiode = new PeriodeConfiguration();
    

    DishesDB() {
    }

    /**
     * Diese Methode fügt eine Speise dem HashSet speise hinzu.
     *
     * @param dish eine Speise.
     */
    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    /**
     * Diese Methode zählt die Anzahl der verfügbaren Speisen.
     *
     * @return Anzahl der verfügbaren Speisen.
     */
    public int numberOfDishes() {
        return dishes.size();
    }

    /**
     * Mit dieser Methode kann eine Speise nach Name gesucht werden.
     *
     * @param speisenName Der Name der Speise.
     * @return gibt die Speise wider, wenn sie gefunden wurde.
     * @throws
     * de.vawi.kuechenchefApp.speisen.SpeisenVerwaltung.SpeiseNichtGefunden Wird
     * geworfen, wenn die Speise nicht gefunden wurde.
     */
    public Dish findDishByName(String speisenName) throws SpeiseNichtGefunden {
        for (Dish speise : dishes) {
            if (speise.getName().equals(speisenName)) {
                return speise;
            }
        }
        throw new SpeiseNichtGefunden();
    }

    /**
     * Stellt sicher, dass es immer exakt eine Instanz der SpeisenVerwaltung
     * gibt.
     *
     * @return Gibt die Instanz wider.
     */
    public static DishesDB getInstance() {
        if (INSTANZ == null) {
            INSTANZ = new DishesDB();
        }
        return INSTANZ;
    }

    /**
     * Diese Methode hilft die beliebtestens Speisen zu finden.
     *
     * @param periode Eine Planungsperiode.
     * @return Gibt eine bestimmte Anzahl Speisen wider, die unter den
     * verfügbaren Speisen am beliebtesten sind.
     */
    public List<Dish> findFavorDishes() {
        return new ArrayList<>(dishes);
    }

    public List<Dish> findDishesByCategory(DishCategory category) {
        List<Dish> byCategory = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCategory().equals(category)) {
                byCategory.add(dish);
            }
        }

        return byCategory;
    }

    void delete(Dish speise) {
        dishes.remove(speise);
    }

    class SpeiseNichtGefunden extends RuntimeException {
    }
}
