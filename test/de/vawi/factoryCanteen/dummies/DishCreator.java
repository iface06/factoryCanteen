/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.dummies;

import de.vawi.factoryCanteen.entities.Dish;
import de.vawi.factoryCanteen.entities.DishCategory;

/**
 *
 * @author Tatsch
 */
public class DishCreator {

    private Dish speise = new Dish();

    public DishCreator beliebtheit(int beliebtheit) {
        speise.setPopularity(beliebtheit);
        return this;
    }

    public DishCreator name(String name) {
        speise.setName(name);
        return this;
    }

    public DishCreator category(DishCategory category) {
        speise.setCategory(category);
        return this;
    }

    public Dish create() {
        return speise;
    }
}