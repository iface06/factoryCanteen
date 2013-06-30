package de.vawi.factoryCanteen.app.entities;

import de.vawi.factoryCanteen.app.entities.Dish;
import java.io.Serializable;
import java.util.*;

/**
 * Diese Klasse repraensntiert einen Tag innerhalb eines Speiseplans
 *
 * @author Max
 */
public class Day implements Serializable, Iterable<Dish>, Comparable<Day> {

    private Date day;
    private List<Dish> offerdDishes = new ArrayList<>();

    /**
     * Konstrutkor
     *
     * @param nummer Die Tage innerhalb eines Plans werden druch nummeriert,
     * diese Nummer wird dem Konstrukor mitgegeben.
     */
    public Day(Date day) {
        this.day = day;
    }

    /**
     *
     * @return gibt das Datum des Tages zurueck
     */
    public Date getDay() {
        return day;
    }

    @Override
    public Iterator<Dish> iterator() {
        return offerdDishes.iterator();
    }

    public void insert(Dish dish) {
        offerdDishes.add(dish);
        orderByPopularityOfDishes();
    }

    @Override
    public int compareTo(Day o) {
        return this.getDay().compareTo(o.getDay());
    }

    private void orderByPopularityOfDishes() {
        Collections.sort(offerdDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getPopularity().compareTo(o2.getPopularity());
            }
        });
    }
}
