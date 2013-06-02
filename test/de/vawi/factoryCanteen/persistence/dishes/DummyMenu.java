/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.factoryCanteen.persistence.dishes;

import de.vawi.factoryCanteen.entities.Dish;
import de.vawi.factoryCanteen.entities.Canteen;
import de.vawi.factoryCanteen.entities.Menu;
import de.vawi.factoryCanteen.entities.Day;
import java.util.Date;

/**
 *
 * @author Tatsch
 */
public class DummyMenu {

    private Menu plan = new Menu();
    private Date tage = new Date();

    public DummyMenu forCanteen(Canteen kantine) {
        plan.setKantine(kantine);
        return this;
    }

    public DummyMenu addDay(Dish erste, Dish zweite, Dish dritte) {
        Day t = new Day(tage);
        t.insert(erste);
        t.insert(zweite);
        t.insert(dritte);
        plan.addDay(t);
        return this;
    }

    public Menu create() {
        return plan;
    }
}
