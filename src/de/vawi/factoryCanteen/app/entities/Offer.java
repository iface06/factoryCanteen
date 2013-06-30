package de.vawi.factoryCanteen.app.entities;

import de.vawi.factoryCanteen.app.entities.Dish;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Tobias
 */
public class Offer implements Serializable {

    private Date date;
    private Dish dish;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public DishCategory getDishCategory() {
        return dish.getCategory();
    }
}
