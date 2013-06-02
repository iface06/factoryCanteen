/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.persistence.dishes;

import de.vawi.factoryCanteen.entities.Dish;
import de.vawi.factoryCanteen.entities.DishCategory;

/**
 *
 * @author Tatsch
 */
public class DummyDish {

    private Dish speise = new Dish();

    public DummyDish popularity(int beliebtheit) {
        speise.setPopularity(beliebtheit);
        return this;
    }

    public DummyDish name(String name) {
        speise.setName(name);
        return this;
    }

    public DummyDish category(DishCategory category) {
        speise.setCategory(category);
        return this;
    }

    public Dish create() {
        return speise;
    }
}
